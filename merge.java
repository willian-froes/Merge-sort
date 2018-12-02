package mergesort;

public class mergesort {
    public static void main(String[] args) {
        
    	//notas:
    	//o método de ordenação de vetores por comparação Merge Sort é do tipo dividir-para-conquistar onde o mesmo
    	//se baseia em uma ordenação por mistura. O objetivo desse método é dividir o problema em subrpoblemas e
    	//ordenar os valores através da recursividade(quando o método chama ele mesmo). Após utilizar a recursividade
    	//o Merge Sort utiliza o método de conquista, logo após os subproblemas terem sido resolvidos, há uma união
    	//dos mesmos. Como esse algoritmo utiliza recursividade, o mesmo consome um nivel alto de memória e necessita
    	//de um maior tempo para execução, logo é correto afirmar que esse método não é eficiente em alguns casos.
    	
    	//inicializando o vetor array com valores desordenados
    	//podem inserir outros valores, funciona da mesma forma
    	int []array= {4, 2, 5, 1, 0, 9, 6, 8, 7, 3};
        
    	//printando o vetor com valores desordenados
        System.out.print("vetor desordenado = {");
		for (int i=0; i<array.length;i++) {
			if (i > 0) {
				System.out.print(", ");
			}
			System.out.print(array[i]);
		}
		System.out.println("}");
        
        //método mergeSort ao qual ordena o vetor dividindo o mesmo recursivamente
        mergeSort(array,0,array.length-1);

        //printando o vetor com valores ordenados
        System.out.print("vetor ordenado = {");
		for (int i=0; i<array.length;i++) {
			if (i > 0) {
				System.out.print(", ");
			}
			System.out.print(array[i]);
		}
		System.out.print("}");
    }

    //método mergeSort recebe vetor array, indice do início e fim
    public static void mergeSort(int []array, int start,int end){
        if(start == end) return; //se o indice inicial é igual ao final, retorna apenas com o vetor já printado
        int middle = (start + end)/2; //senão, meio do vetor é igual a soma do indice do inicio com fim, divido por 2
        mergeSort(array, start, middle); //método mergeSort recebendo array, indice do inicio e meio+1
        mergeSort(array, middle + 1, end); //método mergeSort recebendo array, indice do meio+1 e final
        
        //OBS: através dos dois mergeSort a cima, é possível dividir o vetor em dois sub-vetores e assum sucessivamente
        
        //método mergeIntercalator: intercala os valores dos sub-vetores posicionando novamente no vetor inicial 
        mergeIntercalator(array, start, end);
    }

    //método mergeIntercalator recebe vetor array, indice do início e fim
    public static void mergeIntercalator(int []array, int start, int end){
        int []aux= new int [array.length]; //inicializando o vator auxliar para a intercalação de valores
        int size = end - start + 1; //tamanho da intercalação é igual ao indice do fim, menos indice do inicio + 1
        int middle = (start + end) /2; //meio é igual ao indice do inicio + fim, divido por 2
        for (int i = start, j = middle + 1, k = 0; k < size; k++){
        	//se indice i=0 é maior que o meio ou indice j é menor ou igual ao fim e indice i do array e maior que indice j do array, então:
            if(i > middle || (j <= end && array[i] > array[j])){
                aux[k] = array[j]; //posição [k] do vetor auxiliar recebe valor do array na posição [j]
                j++; //incrementando j
            } else {
            	aux[k] = array[i]; //senão posição k do vetor auxiliar recebe valor na posição i do array
            	i++; //incrementando i
            }
        }
        for (int i = 0; i < size; i++) {
            array[start + i] = aux[i]; //array na posição inicial + indice i recebe o vetor auxiliar na posição i
        }
    }
}
