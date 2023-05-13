import java.util.*;

public class Main {
    static Scanner in= new Scanner(System.in);
    static List<Ouvinte> listaOuvinte = new ArrayList<>();
    static int idOuvinte = 1;
    static int [] apostas = new int[3];


    public static void main(String[] args) {
      /*. Criar, editar e eliminar ouvintes.
    2. Ver dados de um ouvinte.
    3 . Ver ranking de ouvintes (número de vezes que jogaram e número de vezes que acertaram).
    4. Jogar*/

        listaOuvinte = new ArrayList<>();
       InicializarOuvinte();

        /*menu*/
        int op = 1;

        while (op != 0) {
            System.out.println("Bem vindo ao Jogo do Saco! \n O objetivo do jogo é acertar o peso do saco" +
                    "e tens uma margem de erro de 150 gramas. \n\nBoa sorte");
            System.out.println("\n1-Criar Ouvinte");
            System.out.println("2- Editar Ouvinte");
            System.out.println("3-Eliminar Ouvinte");
            System.out.println("4-Dados do Ouvintte");
            System.out.println("5-Ver Ranking");
            System.out.println("6-Jogar");
            System.out.println("0-Sair");
            op = in.nextInt();

            switch (op) {
                case 0 -> {
                    return;
                }
                case 1 -> Criar();
                case 2 -> Editar();
                case 3 -> Eliminar();
                case 4 -> Dados();
                case 5 -> Ranking();
                case 6 -> Jogar();
                
                default -> {
                    System.out.println("Opção inválida");
                    return;
                }
            }
        }
    }




    /*DADOS TESTES*/
    private static void InicializarOuvinte() {
    }
    private static void  Init (){
        listaOuvinte.add(new Ouvinte(1,"João",30,5,2));
        listaOuvinte.add(new Ouvinte(2, "Maria",25,2,1));
        listaOuvinte.add(new Ouvinte(3, "Laila",35,6,6));
        listaOuvinte.add(new Ouvinte(4, "Sara",32,2,2));
        listaOuvinte.add(new Ouvinte(5, "Ricardo",31,10,4));
        idOuvinte=6;
    }
    private static void Jogar() {
        /* fazer um radon aleatorio para saber o valor do peso do saco*/
        /* fazer jogo de palpite do ouvinte*/
        /* o ouvinte que chegar mais perto é o vencedor*/
        Random rnd = new Random();

        int nrJogadores = 5;

        // variaveis para encontrar limite inferior e superior, para gerar valor random do peso do saco.
        int pesoMin = 1500;
        int pesoMax = 3000;
        int limInferiorPesoSaco = 0;
        int limSuperiorPesoSaco = limInferiorPesoSaco + 150;
        int pesodoSaco;


        System.out.println("Bem vindo ao JOGO DO SACO! \n O objetivo e acertar no peso de um saco. Tens uma margem de erro de 150g.");
        System.out.println("");
        System.out.println("Os ouvintes que vão jogar hoje são...");
        System.out.println("...\n...\n...");

        Collections.shuffle(listaOuvinte);

        for (int i = 0; i < nrJogadores; i++) {
            System.out.println("Em " + (i + 1) + " º lugar o(a) " + listaOuvinte.get(i).getNome());
        }

        // Gerar valor random do peso do saco
        limInferiorPesoSaco = rnd.nextInt(pesoMin, pesoMax);
        limSuperiorPesoSaco = limInferiorPesoSaco + 150;
        pesodoSaco = rnd.nextInt(limInferiorPesoSaco, limSuperiorPesoSaco);


        System.out.println("");
        System.out.println("Caros Ouvintes, o saco pesa entre " + limInferiorPesoSaco + "gr e " + limSuperiorPesoSaco + "gr.");
        System.out.println("Digam o valor do saco, em gramas. Por exemplo: 1150gr");
        System.out.println("");

        for (int i = 0; i < nrJogadores; i++) {
            int aposta = 0;
            System.out.println(listaOuvinte.get(i).getNome() + ": Qual é a sua aposta?");
            Scanner in = new Scanner(System.in);
            if (in.hasNextInt()) {
                aposta = in.nextInt();
            }else{
                System.out.println("Valor errado. Por favor introduza um numero inteiro.");
            }
            boolean apostaRepetida = false;
            if (aposta >= limInferiorPesoSaco && aposta <= limSuperiorPesoSaco) {
                for (int j = 0; j < nrJogadores; j++) {
                    if (aposta == apostas[j]) {
                        System.out.println("Está valor ja foi inserido. Tente outra aposta");
                        apostaRepetida = true;
                        i--;
                        break;
                    }

                } if(!apostaRepetida){
                    apostas[i] = aposta;
                    listaOuvinte.get(i).setJogadas(listaOuvinte.get(i).getJogadas() + 1);
                }

            } else {
                System.out.println("Aposte num valor entre " + limInferiorPesoSaco + " e " + limSuperiorPesoSaco);
                i--;
            }
        }

        for (int i = 0; i < nrJogadores; i++) {
            System.out.println("0(A) " + listaOuvinte.get(i).getNome() + " diz que o saco tem " + apostas[i] + "gr.");
        }

        // Verificação do valor que mais se aproxima do valor do saco
        int distance = Math.abs(apostas[0] - pesodoSaco);
        int index = 0;
        for (int i = 1; i < apostas.length; i++) {
            int cdistance = Math.abs(apostas[i] - pesodoSaco);
            if (cdistance < distance) {
                index = i;
                distance = cdistance;
            }
        }

        int melhorAposta = apostas[index];
        System.out.println("-----------------------------------------");
        System.out.println("O peso do Saco é " + pesodoSaco + "gr.");
        System.out.println("O peso mais aproximado do peso do Saco é " + melhorAposta + "gr. \nO(A) GRANDE VENCEDOR(A) é o(a)" + listaOuvinte.get(index).getNome() + ".");
        System.out.println("-----------------------------------------");

        listaOuvinte.get(index).setVitorias(listaOuvinte.get(index).getVitorias()+1); //Incrementa o nr de acertos

    }


    private static void Ranking() {


            // Ordenação decrescente do nr de acertos

            for (int i = 0; i < listaOuvinte.size(); i++) {
                for (int j = i + 1; j < listaOuvinte.size(); j++) {
                    if (listaOuvinte.get(i).getVitorias() < listaOuvinte.get(j).getVitorias()) {

                        Collections.swap(listaOuvinte, i, j);
                    }
                }
                System.out.println(listaOuvinte.get(i).toString() + "\nAcertos: " + listaOuvinte.get(i).getVitorias() + "\nJogadas: " + listaOuvinte.get(i).getVitorias());
                System.out.println("-------------------------");
            }
    }

    private static void Dados() { //falta coisas//


        System.out.println("-------------------------------");
        for (int i = 0; i < listaOuvinte.size(); i++) {
            System.out.println(listaOuvinte.get(i));
            System.out.println("------------------------");
        }


    }

            private static void Eliminar() {
        System.out.println("Qual o ID do Ouvinte eliminar?");
        int id = in.nextInt();

        for (int i = 0; i < listaOuvinte.size(); i++) {
            if (listaOuvinte.get(i).getId() == id) {
                /*listaOuvinte.remove(i);*/
                System.out.println("Ouvinte removido com sucesso");
                /*return;*/
            }
        }
        /*System.out.println("Erro ao remover o Ouvinte");*/
    }

    /* private static void EliminarOuvintes() {
        Scanner in = new Scanner(System.in);

        for (int i = 0; i < listaOuvinte.size(); i++) {
            System.out.println(listaOuvinte.get(i));
        }

        System.out.println("Qual o ID do Ouvinte que quer eliminar?");
        int numID = in.nextInt();

        for (int i = 0; i < listaOuvinte.size(); i++) {
            if (numID == listaOuvinte.get(i).getId()) {
                listaOuvinte.remove(i);
                System.out.println("Ouvinte eliminado com sucesso");
                return;
            }
        }
        System.out.println("Nao existe um Ouvinte com este ID");
    }*/
    private static void Editar() {
    Scanner in = new Scanner(System.in);
    for( int i= 0; i<listaOuvinte.size();i++){
        System.out.println(listaOuvinte.get(i));
    }
        System.out.println("Qual o número do Id do Ouvinte a deseja editar?");
    int numID = in.nextInt();

        for (int i = 0; i < listaOuvinte.size(); i++) {
            if (numID == listaOuvinte.get(i).getId()) {
                System.out.println("Que informacao do ouvinte quer editar? \n1:Nome \n2:Idade ");
                int op = in.nextInt();

                if (op == 1) {
                    System.out.println("Edite o nome");
                    String nome = in.next();
                    listaOuvinte.get(i).setNome(nome);
                    System.out.println("Nome editado com sucesso");
                } else if (op == 2) {
                    System.out.println("Edite a idade");
                    int idade = in.nextInt();
                    listaOuvinte.get(i).setIdade(idade);
                    System.out.println("Idade editada com sucesso");
                } else {
                    System.out.println("Opcao invalida");
                    return;
                }
                return;
            }
            System.out.println("Nao existe um Ouvinte com este ID");
        }
    }

    private static void Criar() {


        in = new Scanner(System.in);
        System.out.println("Qual o nome do Ouvinte?");
        String nome = in.nextLine();

        in = new Scanner(System.in);
        System.out.println("Qual a idade do Ouvinte?");
        int idade = in.nextInt();

        listaOuvinte.add(new Ouvinte(idOuvinte, nome, idade,0,0));
        idOuvinte++;
        System.out.println("Ouvinte adicionado com sucesso!");
        return;
    }
}
