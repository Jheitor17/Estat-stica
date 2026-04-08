public class BuscaSequencial {
    public static int buscar(int[] vetor, int alvo) {

        for(int i = 0; i < vetor.length; i++) {

            if(vetor[i] == alvo) {
                return i;
            }
        }

        return -1;
    }
}
