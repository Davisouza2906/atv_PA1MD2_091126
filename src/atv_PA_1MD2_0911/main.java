package atv_PA_1MD2_0911;

import javax.swing.JOptionPane;

public class main {

    static String[] nomes = new String[100];
    static int[] pontos = new int[100];
    static int total = 0;

    public static void main(String[] args) {
        int novaPartida;
        do {
            String nome = JOptionPane.showInputDialog("Digite seu nome:");
            if (nome == null) {
                break;
            }

            int p = jogar();
            nomes[total] = nome;
            pontos[total] = p;
            total++;

            JOptionPane.showMessageDialog(null, "Jogador: " + nome + "\nPontuação: " + p);
            novaPartida = JOptionPane.showConfirmDialog(null, "Jogar uma nova partida?", "Nova partida",
                    JOptionPane.YES_NO_OPTION);

            mostrarRanking();
        } while (novaPartida == JOptionPane.YES_OPTION);
    }

    static int jogar() {
        // FASE 1 - Pedra, Papel e Tesoura (melhor de 3)
        String[] op = {"Pedra", "Papel", "Tesoura"};
        int meus = 0;
        int pc = 0;
        while (meus < 2 && pc < 2) {
            int j;
            do {
                j = JOptionPane.showOptionDialog(null, "Sua jogada:", "Fase 1",
                        JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, op, op[0]);
            } while (j < 0);
            int c = (int) (Math.random() * 3);

            String msg = "Você: " + op[j] + "\nComputador: " + op[c] + "\n";
            if (j == c) {
            	pc++;
                msg += "Empate!";
            } else if ((j - c + 3) % 3 == 1) {
                meus++;
                msg += "Você ganhou a rodada! +15";
            } else {
                pc++;
                msg += "Computador ganhou a rodada.";
            }
            JOptionPane.showMessageDialog(null, msg);
        }
        int soma = meus * 15;
        if (meus < 2) {
            JOptionPane.showMessageDialog(null, "Você perdeu. Fim de jogo!");
            return soma;
        }

        // FASE 2 - Java (precisa de 60 pontos)
        int f2 = 0;
        f2 += pergunta("Qual método do JOptionPane pede para o usuário digitar?",
                "showMessageDialog", "showInputDialog", "showConfirmDialog", 2);
        f2 += pergunta("Qual tipo guarda o valor 3.14?", "int", "boolean", "double", 3);
        f2 += pergunta("Qual estrutura repete enquanto a condição for verdadeira?", "while", "if", "break", 1);
        f2 += pergunta("Qual é o índice do primeiro elemento de um array?", "1", "0", "-1", 2);
        f2 += pergunta("Qual operador compara se dois valores são iguais?", "=", "!=", "==", 3);
        soma += f2;
        if (f2 < 60) {
            JOptionPane.showMessageDialog(null, "Você fez " + f2 + " pontos na Fase 2 (mínimo 60). Fim de jogo!");
            return soma;
        }

        // FASE 3 - Sistema Solar
        int f3 = 0;
        f3 += pergunta("Qual é o maior planeta do Sistema Solar?", "Saturno", "Júpiter", "Netuno", 2);
        f3 += pergunta("Qual planeta é chamado de Planeta Vermelho?", "Marte", "Vênus", "Mercúrio", 1);
        f3 += pergunta("Qual astro está no centro do Sistema Solar?", "Lua", "Terra", "Sol", 3);
        f3 += pergunta("Qual planeta é o mais próximo do Sol?", "Terra", "Mercúrio", "Marte", 2);
        f3 += pergunta("Qual planeta é famoso por seus anéis?", "Marte", "Saturno", "Mercúrio", 2);
        f3 += pergunta("Quantos planetas tem o Sistema Solar?", "9", "7", "8", 3);
        f3 += pergunta("Qual é o satélite natural da Terra?", "Lua", "Fobos", "Europa", 1);
        f3 += pergunta("Qual é o planeta mais quente?", "Mercúrio", "Vênus", "Marte", 2);
        f3 += pergunta("Em qual galáxia fica o Sistema Solar?", "Andrômeda", "Triângulo", "Via Láctea", 3);
        f3 += pergunta("Quem foi o primeiro humano a pisar na Lua?", "Yuri Gagarin", "Neil Armstrong",
                "Alan Shepard", 2);
        return soma + f3;
    }

    // Faz uma pergunta e devolve 15 se acertou, 0 se errou
    static int pergunta(String texto, String a, String b, String c, int certa) {
        String[] op = {a, b, c};
        int r = JOptionPane.showOptionDialog(null, texto, "Pergunta",
                JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, op, op[0]);
        if (r == certa - 1) {
            JOptionPane.showMessageDialog(null, "Acertou! +15");
            return 15;
        }
        JOptionPane.showMessageDialog(null, "Errou! A resposta certa era: " + op[certa - 1]);
        return 0;
    }

    static void mostrarRanking() {
        // ordena do maior para o menor pontuação
        for (int i = 0; i < total - 1; i++) {
            for (int k = i + 1; k < total; k++) {
                if (pontos[k] > pontos[i]) {
                    int pt = pontos[i];
                    pontos[i] = pontos[k];
                    pontos[k] = pt;
                    String nm = nomes[i];
                    nomes[i] = nomes[k];
                    nomes[k] = nm;
                }
            }
        }
        String texto = "";
        for (int i = 0; i < total; i++) {
            texto += (i + 1) + "º - " + nomes[i] + ": " + pontos[i] + " pontos\n";
        }
        JOptionPane.showMessageDialog(null, texto, "RANKING", JOptionPane.INFORMATION_MESSAGE);
    }
} 