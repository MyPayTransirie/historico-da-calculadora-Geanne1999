# Exercicio 2 - Calculadora com Histórico

Esta tarefa foi pensada para exercitar a passagem de informação entre `Activities` diferentes e exercitar a programação em Kotlin.

Melhore a calculadora do exercício 1 acrescentando um botão para mostrar o histórico de cálculos em uma segunda tela (`Activity`). Utilizaremos diversas formas de passar a informação para fins de treino do uso da linguagem Kotlin.

Indique quais partes completou e descreva as dificuldades que teve.

## Etapas

Abaixo segue alguns passos sugeridos para criar a aplicação:

1. Acrescente o botão para abrir mostrar o histórico
2. Crie uma segunda `Activity` com um `TextView` de id `txtHistorico`
3. Associe o listener para que, ao apertar o botão, a segunda `Activity` seja iniciada
4. Sempre que "=" for pressionado, salve o resultado da operação em uma string com ";". Ex. "10+10=20;5*5=25"
5. Envie a string para a segunda tela utilizando `putExtra('historico', string...)`
6. No `onCreate` da tela 2, obtenha o histórico e "decodifique", separando as expressões e exibindo uma por linha no `TextView`
7. Faça o mesmo do passo 5 e 6, porém armazene no formato JSON, ex. {"expr": "10+10", "result": "20"}
8. Agora não mais utilize uma única string, mas sim um `Array<String>`
9. Implemente uma nova classe "Historico" contendo o histórico de expressões como você achar melhor
10. Por fim, envie um **objeto** Histórico para a próxima tela utilize o padrão de projeto _Serialization Proxy Pattern_ com a interface `Parcelable` para encapsular ele.

## Orientações

- Comente o código desenvolvido explicando o que fez em cada passo
- Caso não tenha dificuldades, procure incrementar a calculadora com mais operações, botão de apagar, entre outros

## Status

| Passo | Descrição  | Completou? |
| ----- | ---------- | ---------  |
|   1  | Botão Histórico                       |      sim      |
|   2  | Tela Histórico                        |      sim      |
|   3  | Listener iniciar tela                 |      sim      |
|   4  | Salvar histórico string ";"           |       sim     |
|   5  | Enviar histórico com string           |       sim     |
|   6  | Exibir histórico na tela 2            |       sim     |
|   7  | Enviar histórico como JSON            |       sim     |
|   8  | Enviar histórico como `Array<String>` |       sim     |
|   9  | Salvar histórico como `Historico`     |       sim     |
|   10 | Enviar histórico com `Parcelable`    |       sim      |

----------
## Dificuldades

Explique de forma resumida as dificuldades que teve e como resolveu.
Dificuldades com o formato json, além dos demais formatos, além de uso de funções nunca utlizadas por mim como o jointToString. Dificuldade com um elemento do layout, porém depois de utilizar log e confirmar o envio das informações, também foi resolvido. 

> Escreva: "não tive dificuldades" se for o caso e apague o resto

1. **Importar biblioteca X**
   - Não consegui importar a biblioteca X por conta disso e daquilo.
   - Resolvi fazendo X, Y e Z...
2. **Avaliação de expressão**
   - Resolvi implementar minha própria e por não conhecer Kotlin demorei bastante...
