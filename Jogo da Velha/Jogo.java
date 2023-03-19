import java.util.Random;
import java.util.Scanner;

public class Jogo {

    private static String XO = "XO";
    private static Random r = new Random();
    private static char simboloAtual = XO.charAt(r.nextInt(XO.length()));
    private static Boolean game = true;
    private static Campo velha[][] = new Campo[3][3];
    private static char vitoria = ' ';
    private static Scanner scan = new Scanner(System.in);

    public static void main (String[] args) {
    
        for (int l=0; l<3; l++)
        for(int c=0; c<3; c++) velha[l][c] = new Campo();
        
        
        while(game){
            //limparTela();
            desenharJogo();

            if(verificarVitoria2()){
                game = false;
                break;
            }

            if(verificarVelha()){
                game = false;
                break;
            }
            
            try {jogar();}
            catch (Exception e){
                System.out.println("ERRO");
                game = false;
                break;
            }

            if(simboloAtual == 'X') simboloAtual = 'O';
            else simboloAtual = 'X';
        }

        System.out.println("FIM DE JOGO.");
        if(vitoria != ' ') System.out.println("Vencedor: " + vitoria);
        else System.out.println("Deu velha!");
    }

    public static void limparTela(){
        for(int i=0; i<100; i++) System.out.print("\n");
    }

    public static void desenharJogo(){
        System.out.println("\n    0   1    2");
        System.out.printf("0   %c | %c | %c%n", velha[0][0].getSimbolo(), velha[0][1].getSimbolo(), velha[0][2].getSimbolo());
        System.out.println("  -------------");
        System.out.printf("1   %c | %c | %c%n", velha[1][0].getSimbolo(), velha[1][1].getSimbolo(), velha[1][2].getSimbolo());
        System.out.println("  -------------");
        System.out.printf("2   %c | %c | %c%n%n", velha[2][0].getSimbolo(), velha[2][1].getSimbolo(), velha[2][2].getSimbolo());
    }

    public static void jogar(){
        System.out.printf("Jogador: %c%n", simboloAtual);
        System.out.print("Digite a linha e coluna de sua jogada: ");
        int l = scan.nextInt();
        int c = scan.nextInt();

        if(velha[l][c].getSimbolo() == ' '){
            velha[l][c].setSimbolo(simboloAtual);
        }
        else {
            System.out.print("Jogada inválida, tente novamente.\n");
            jogar();
        }
    }

    public static Boolean verificarVelha(){
        Boolean deuVelha = true;

        for (int l=0; l<3; l++)
        for(int c=0; c<3; c++) 
            if(velha[l][c].getSimbolo() == ' ') deuVelha = false;

        return deuVelha;
    }

    // algoritmo alternativo (inicial) para verificar vitória
    public static Boolean verificarVitoria() {
        for(int c=0; c<3; c++){
            if(velha[0][c].getSimbolo() == velha[1][c].getSimbolo() && velha[0][c].getSimbolo() == velha[2][c].getSimbolo()){
                if(velha[0][c].getSimbolo() != ' '){
                    vitoria = velha[0][c].getSimbolo();
                    return true;
                }
            }
        }
        for(int l=0; l<3; l++){
            if(velha[l][0].getSimbolo() == velha[l][1].getSimbolo() && velha[l][0].getSimbolo() == velha[l][2].getSimbolo()){
                if(velha[l][0].getSimbolo() != ' '){
                    vitoria = velha[l][0].getSimbolo();
                    return true;
                }
            }
        }
        if(velha[0][0].getSimbolo() == velha[1][1].getSimbolo() && velha[0][0].getSimbolo() == velha[2][2].getSimbolo()){
            if(velha[0][0].getSimbolo() != ' '){
                vitoria = velha[0][0].getSimbolo();
                return true;
            }
        }
        if(velha[0][2].getSimbolo() == velha[1][1].getSimbolo() && velha[0][2].getSimbolo() == velha[2][0].getSimbolo()){
            if(velha[0][2].getSimbolo() != ' '){
                vitoria = velha[0][2].getSimbolo();
                return true;
            }
        }
        return false;
    }

    public static Boolean verificarVitoria2() {
        char[] simbolos = {'X','O'};
        for(int s=0; s<2; s++){
            int cont=0;

            //verifica linhas
            for(int l=0; l<3; l++){
                for(int c=0; c<3; c++){
                    if(velha[l][c].getSimbolo() == simbolos[s]) cont++;
                }
                if (cont == 3){
                    vitoria = simbolos[s];
                    return true;
                }
                else cont = 0;
            }

            //verifica colunas
            for(int c=0; c<3; c++){
                for(int l=0; l<3; l++){
                    if(velha[l][c].getSimbolo() == simbolos[s]) cont++;
                }
                if (cont == 3){
                    vitoria = simbolos[s];
                    return true;
                }
                else cont = 0;
            }

            //verifica diagonais
            for(int i=0; i<3; i++){
                if(velha[i][i].getSimbolo() == simbolos[s]) cont++;
            }
            if (cont == 3){
                vitoria = simbolos[s];
                return true;
            }
            else cont = 0;

            for(int i=0; i<3; i++){
                if(velha[i][2-i].getSimbolo() == simbolos[s]) cont++;
            }
            if (cont == 3){
                vitoria = simbolos[s];
                return true;
            }
            else cont = 0;
        }

        return false;
    }
}

