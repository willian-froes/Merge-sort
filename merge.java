package mergesort;

public class merge {
	public static void main(String[] args) {
		//o método de ordenação de vetores por comparação Merge Sort é do tipo dividir-para-conquistar onde o mesmo
		//se baseia em uma ordenação por mistura. O objetivo desse método é dividir o problema em subrpoblemas e
		//ordenar os valores através da recursividade(quando o método chama ele mesmo). Após utilizar a recursividade
		//o Merge Sort utiliza o método de conquista, logo após os subproblemas terem sido resolvidos, há uma união
		//dos mesmos. Como esse algoritmo utiliza recursividade, o mesmo consome um nivel alto de memória e necessita
		//de um maior tempo para execução, logo é correto afirmar que esse método não é eficiente em alguns casos.
		int array[] = {7, 3, 2, 8, 1, 9, 6, 0, 4, 5};
		int aux[] = new int[array.length];
		
		System.out.println(array[0] == 7); //true
		System.out.println(array[1] == 3); //true
		System.out.println(array[2] == 2); //true
		System.out.println(array[3] == 8); //true
		System.out.println(array[4] == 1); //true
		System.out.println(array[5] == 9); //true
		System.out.println(array[6] == 6); //true
		System.out.println(array[7] == 0); //true
		System.out.println(array[8] == 4); //true
		System.out.println(array[9] == 5); //true
		
		System.out.print("vetor = {");
		for (int i=0; i<array.length;i++) {
			if (i > 0) {
				System.out.print(",");
			}
			System.out.print(array[i]);
		}
		System.out.print("}");
		//vetor = {7,3,2,8,1,9,6,0,4,5}
	}
}
