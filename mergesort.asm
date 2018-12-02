.data					# Definindo seguimento de dados que serao utilizados no programa;
eol:	.asciiz	", "			# Definindo separador dos valores na impressão final, pode ser também: "\n"

# Definindo valores do vetor, lembrando que para adicionar um novo, é necessário: valueforwrite:  .word   value;
eight:	.word	8
five:	.word	5
four:	.word	4
nine:	.word	9
one:	.word	1
seven:	.word	7
zero:	.word   0
six:	.word	6
ten:	.word	10
three:	.word	3
two:	.word	2

# Tamanho do vetor em length e informações dos valores, ao adicionar um novo valor, aumentar o tamanho e adicionar valueforwirte;
length:	.word	11
info:	.word	seven
	.word	three
	.word	ten
	.word	one
	.word	five
	.word	two
	.word	nine
	.word	eight
	.word	four
	.word	six
	.word   zero

.text					# Definindo seguimento de instruções do programa;
	la	$a0, info		# Lendo o valor inicial do vetor;
	lw	$t0, length		# Lendo o tamanho do vetor;
	sll	$t0, $t0, 2		# Multiplica o tamanho da matriz por 4(tamanho por valor);
	add	$a1, $a0, $t0		# Soma para calcular o final da matriz;
	jal	mergesort		# Chamando a função mergesort;
  	b	sortend			# Chamando a função para intercalação dos valores ordenados;
	
mergesort:				# Função merge sort;
	addi	$sp, $sp, -16		# Ajustando o apontador de pilha (stack pointer);
	sw	$ra, 0($sp)		# Armazenando o endereço de retorno na pilha;
	sw	$a0, 4($sp)		# Armazenando o endereço inicial do vetor na pilha;
	sw	$a1, 8($sp)		# Armazenando o endereço final do vetor na pilha;
	sub 	$t0, $a1, $a0		# Diferença entre o endereço inicial e final, sendo o número de elementos*4;
	ble	$t0, 4, mergesortend	# Se o vetor conter apenas um número, apenas retorna;
	srl	$t0, $t0, 3		# Dividindo o tamanho do vetor por 8 para a metade do número de elementos;
	sll	$t0, $t0, 2		# Multiplicando o valor da linha anterior por 4 para se obter a metade do tamanho do vetor;
	add	$a1, $a0, $t0		# Calcular o indice do meio do vetor;
	sw	$a1, 12($sp)		# Armazenando o endereço do meio do vetor na pilha;
	jal	mergesort		# Chamada recursiva do mergesort na primeira metade do vetor;
	lw	$a0, 12($sp)		# Lendo o endereço do meio do vetor da pilha;
	lw	$a1, 8($sp)		# Lendo o endereço final do vetor da pilha;
	jal	mergesort		# Chamada recursiva do mergesort na segunda metade do vetor;
	lw	$a0, 4($sp)		# Lendo o endereço inicial do vetor da pilha;
	lw	$a1, 12($sp)		# Lendo o endereço do meio do vetor da pilha;
	lw	$a2, 8($sp)		# Lendo o endereço final do vetor da pilha;
	jal	merge			# Mesclando as duas metades do vetor;
	
mergesortend:				# Função que retorna o valor final do mergesort
	lw	$ra, 0($sp)		# Lendo o endereço de retorno da pilha;
	addi	$sp, $sp, 16		# Ajustando o apontador de pilha (stack pointer);
	jr	$ra			# retorno 
	
merge:					# Função que junta novamente as partes do vetor;
	addi	$sp, $sp, -16		# Ajusta o apontador de pilha (stack pointer);
	sw	$ra, 0($sp)		# Armazenando o endereço de retorno da pilha;
	sw	$a0, 4($sp)		# Armazenando o endereço inicial da pilha;
	sw	$a1, 8($sp)		# Armazenando o endereço do meio da pilha;
	sw	$a2, 12($sp)		# Armazenando o endereço final da pilha;
	move	$s0, $a0		# Criando uma cópia da primeira metade da pilha;
	move	$s1, $a1		# Criando uma cópia da segunda metade da pilha;
	
mergeloop:
	lw	$t0, 0($s0)		# Lendo o ponteiro da primeira metade do ponteiro;
	lw	$t1, 0($s1)		# Lendo o ponteiro da segunda metade do ponteiro;
	lw	$t0, 0($t0)		# Lendo o primeiro valor do meio;
	lw	$t1, 0($t1)		# Lendo o segundo valor do meio;
	bgt	$t1, $t0, noshift	# Se o valor mais baixo é o primeiro valor, então não muda;
	move	$a0, $s1		# Lendo o argumento para o elemento movido;
	move	$a1, $s0		# Lendo o argumento do endereço para mover para...;
	jal	shift			# Movendo o valor para uma nova posição; 
	addi	$s1, $s1, 4		# Incrementa o indice da segunda metade do vetor;

noshift:				# Caso não seja necessário mover o valor de posição;
	addi	$s0, $s0, 4		# Incrementa o indice da primeira metade do vetor;
	lw	$a2, 12($sp)		# Relendo o endereço final;
	bge	$s0, $a2, mergeloopend	# Encerra o loop quando as duas metades são vazias;
	bge	$s1, $a2, mergeloopend	# Encerra o loop quando as duas metades são vazias;
	b	mergeloop		# Chamada do loop;
	
mergeloopend:
	lw	$ra, 0($sp)		# Lendo o endereço de retorno;
	addi	$sp, $sp, 16		# Ajustando o apontador de pilha (stack pointer);
	jr 	$ra			# Retorno

shift:					# Função para mover o valor para determinadas posições até que fique ordenado; 
	li	$t0, 10			# Lendo o indice no vetor;
	ble	$a0, $a1, shiftend	# Se tiver valor no local, deve parar de mover;
	addi	$t6, $a0, -4		# Buscando o endereço anterior no vetor;
	lw	$t7, 0($a0)		# Lendo o ponteiro atual;
	lw	$t8, 0($t6)		# Lendo o ponteiro anterior;
	sw	$t7, 0($t6)		# Salvando o ponteiro atual para o endereço anterior;
	sw	$t8, 0($a0)		# Salvando o ponteiro anterior no endereço atual;
	move	$a0, $t6		# Mudando para a posição atual novamente;
	b 	shift			# Loop novamente;
	
shiftend:				# Função para encerrar o loop do shift;
	jr	$ra			# Retorno;
	
sortend:				# Função para apontar quando a ordenação estiver completa;
	li	$t0, 0			# Lendo o indice atual;
	
prloop:
	lw	$t1,length		# Lendo o comprimento do vetor;
	bge	$t0,$t1,prdone		# Se chegar ao final do vetor, então finalizar loop e encerrar o programa;
	sll	$t2,$t0,2		# Multiplicando o indice por 4(tamanho por valor);
	lw	$t3,info($t2)		# Lendo o ponteiro;
	lw	$a0,0($t3)		# Lendo o valor apontado e armazenando para impressão;
	li	$v0,1			# Lendo um valor inteiro;
	syscall				# Informando o valor através da chamada do sistema;
	la	$a0,eol			# Definindo para imprimir o valor em uma nova linha;
	li	$v0,4			# Imprimindo o valor;
	syscall				# Chamada de sistema;
	addi	$t0,$t0,1		# Incrementar o indice atual;
	b	prloop			# Retorna ao bloco de impressão novamente;
	
prdone:					# Encerramento do programa;
	li	$v0,10			# Encerrando o programa;
	syscall				# CHamada de sistema;