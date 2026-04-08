import java.util.Scanner;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcao;

        do {
            System.out.println("    SISTEMA DE BUSCA    ");
            System.out.println("1. Executar experimentos automáticos");
            System.out.println("2. Busca manual");
            System.out.println("0. Sair");
            System.out.print("Escolha: ");
            opcao = scanner.nextInt();

            switch (opcao) {
                case 1 -> executarExperimentos();
                case 2 -> buscaManual(scanner);
                case 0 -> System.out.println("Encerrando...");
                default -> System.out.println("Opção inválida.");
            }
        } while (opcao != 0);

        scanner.close();
    }

    private static void executarExperimentos() {
        int[] tamanhos = {500, 1500, 10000};
        int repeticoes = 30;

        System.out.println("\n=== EXPERIMENTOS COM DADOS ALEATÓRIOS ===\n");

        for (int tamanho : tamanhos) {
            System.out.println("--- Tamanho: " + tamanho + " elementos ---");

            int[] dados = GeradorDados.gerarAleatorio(tamanho);

            BuscaEmArvore arvore = new BuscaEmArvore();
            for (int valor : dados) arvore.inserir(valor);

            Arrays.sort(dados);

            int alvo = dados[tamanho / 2];

            double[] temposSequencial = new double[repeticoes];
            double[] temposBinaria = new double[repeticoes];
            double[] temposArvore = new double[repeticoes];

            for (int i = 0; i < repeticoes; i++) {
                long inicio, fim;

                inicio = System.nanoTime();
                BuscaSequencial.buscar(dados, alvo);
                fim = System.nanoTime();
                temposSequencial[i] = fim - inicio;

                inicio = System.nanoTime();
                BuscaBinaria.buscar(dados, alvo);
                fim = System.nanoTime();
                temposBinaria[i] = fim - inicio;

                inicio = System.nanoTime();
                arvore.buscar(alvo);
                fim = System.nanoTime();
                temposArvore[i] = fim - inicio;
            }

            System.out.printf("Busca Sequencial  -> Média: %.2f ns | Desvio: %.2f ns%n",
                    Estatistica.media(temposSequencial), Estatistica.desvioPadrao(temposSequencial));
            System.out.printf("Busca Binária     -> Média: %.2f ns | Desvio: %.2f ns%n",
                    Estatistica.media(temposBinaria), Estatistica.desvioPadrao(temposBinaria));
            System.out.printf("Busca em Árvore   -> Média: %.2f ns | Desvio: %.2f ns%n",
                    Estatistica.media(temposArvore), Estatistica.desvioPadrao(temposArvore));
            System.out.println();
        }
    }

   private static void buscaManual(Scanner scanner) {
        System.out.print("Tamanho do vetor: ");
        int tamanho = scanner.nextInt();

        int[] dados = GeradorDados.gerarAleatorio(tamanho);

        BuscaEmArvore arvore = new BuscaEmArvore();
        for (int valor : dados) arvore.inserir(valor);

        Arrays.sort(dados);

        System.out.print("Escolha um índice entre 0 e " + (tamanho - 1) + ": ");
        int indice = scanner.nextInt();
        int alvo = dados[indice];

        System.out.println("\nBuscando pelo valor: " + alvo);

        long inicioSeq = System.nanoTime();
        int resSeq = BuscaSequencial.buscar(dados, alvo);
        long fimSeq = System.nanoTime();
        double tempoSeq = (fimSeq - inicioSeq) / 1000000.0;

        long inicioBin = System.nanoTime();
        int resBin = BuscaBinaria.buscar(dados, alvo);
        long fimBin = System.nanoTime();
        double tempoBin = (fimBin - inicioBin) / 1000000.0;

        long inicioArv = System.nanoTime();
        boolean resArv = arvore.buscar(alvo);
        long fimArv = System.nanoTime();
        double tempoArv = (fimArv - inicioArv) / 1000000.0;

        System.out.printf("Busca Sequencial: índice %d | Tempo: %.4f ms%n", resSeq, tempoSeq);
        System.out.printf("Busca Binária:    índice %d | Tempo: %.4f ms%n", resBin, tempoBin);
        System.out.printf("Busca em Árvore:  %s | Tempo: %.4f ms%n", (resArv ? "encontrado" : "não encontrado"), tempoArv);
    }
}
