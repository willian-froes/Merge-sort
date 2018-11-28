package mergesort;

public class merge {
	public static void main(String[] args) {
		//o método de ordenação de vetores por comparação Merge Sort é do tipo dividir-para-conquistar onde o mesmo
		//se baseia em uma ordenação por mistura. O objetivo desse método é dividir o problema em subrpoblemas e
		//ordenar os valores através da recursividade(quando o método chama ele mesmo). Após utilizar a recursividade
		//o Merge Sort utiliza o método de conquista, logo após os subproblemas terem sido resolvidos, há uma união
		//dos mesmos. Como esse algoritmo utiliza recursividade, o mesmo consome um nivel alto de memória e necessita
		//de um maior tempo para execução, logo é correto afirmar que esse método não é eficiente em alguns casos.
		
		//declarando um vetor com valores desordenados
		int array[] = {5, 3, 2, 7, 1, 4, 6, 0};
		//declarando um vetor auxiliar para intercalar e, posteriormente, retornar os valores ordenados
		int aux[] = new int[array.length];
		
		//testando os valores do vetor array
		System.out.println(array[0] == 5); //true
		System.out.println(array[1] == 3); //true
		System.out.println(array[2] == 2); //true
		System.out.println(array[3] == 7); //true
		System.out.println(array[4] == 1); //true
		System.out.println(array[5] == 4); //true
		System.out.println(array[6] == 6); //true
		System.out.println(array[7] == 0); //true
		
		//imprimindo o vetor array com valores desordenados
		System.out.print("vetor desordenado = {");
		for (int i=0; i<array.length;i++) {
			if (i > 0) {
				System.out.print(",");
			}
			System.out.print(array[i]);
		}
		System.out.println("}");
		//vetor = {7,3,2,8,1,9,6,0,4,5}
		
		//método mergeSort: realiza a ordenação dos valores do vetor
		//recebe os vetores array e aux, 0 como inicial e final como length-1
		mergeSort(array, aux, 0, array.length-1);
		
		//testando os valores do vetor array, dessa vez, devem estar ordenados
		System.out.println(array[0] == 0); //true
		System.out.println(array[1] == 1); //true
		System.out.println(array[2] == 2); //true
		System.out.println(array[3] == 3); //true
		System.out.println(array[4] == 4); //true
		System.out.println(array[5] == 5); //true
		System.out.println(array[6] == 6); //true
		System.out.println(array[7] == 7); //true
		
		//imprimindo o vetor array com valores ordenados
		System.out.print("vetor ordenado = {");
		for (int i=0; i<array.length;i++) {
			if (i > 0) {
				System.out.print(",");
			}
			System.out.print(array[i]);
		}
		System.out.print("}");
	}

	//FIXME: verificar erro, está retornando vetor ordenado zerado
	private static void mergeSort(int[] array, int[] aux, int start, int end) {
		if(start < end) {
			int medium = (start + end) / 2;
			mergeSort(array, aux, start, medium);
			mergeSort(array, aux, medium+1, end);
			intercalate(array, aux, start, medium, end);
		}
	}

	private static void intercalate(int[] array, int[] aux, int start, int medium, int end) {
		for (int i = start; i <= end; i++) {
			array[i] = aux[i];
			int j = start;
			int k = medium+1;
			
			for (int l = start; l <= end; l++) {
				if(l > medium) {
					array[l] = aux[j++];
				} else {
					if(j > end) {
						array[k] = aux[l++];
					} else {
						if(aux[l] < aux[j]) {
							array[k] = aux[l++];
						} else {
							array[k] = aux[j];
						}
					}
				}
			}
		}
	}
}
