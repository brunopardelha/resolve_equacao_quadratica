package pt.citeforma.java.c7;
// programa para resolver a fórmula resolvente
// outra classe, FormulaResolventeErrors, para resolver os erros

public class FormulaResolvente {

    private static int[] coeficientes = new int[3];
    private static final String ERRORNOTE = "Atenção: ";

    public static boolean validateInputN(String n) {
        // para verificar se é um inteiro válido (integer)
        try {
            if (Double.parseDouble(n) >= Integer.MAX_VALUE || Double.parseDouble(n) <= Integer.MIN_VALUE) {
                // numero inserido maior ou menor que um inteiro (integer)
                throw new FormulaResolventeErrors(1);
            } else {
                return true;
            }
        } catch (FormulaResolventeErrors e) {
            // apresentar erro personalizado
            javax.swing.JOptionPane.showMessageDialog(null, "Encontrado um erro: \n" + e.getMessage());
            return false;
        } catch (NumberFormatException e) {
            // se não for um número apresenter erro customizado
            javax.swing.JOptionPane.showMessageDialog(null, ERRORNOTE + "Nenhum número introduzido!");
            return false;
        } catch (Exception e) {
            // erro geral - se houver
            javax.swing.JOptionPane.showMessageDialog(null, "Encontrado um erro: \n" + e.getMessage());
            return false;
        }
    }

    public static double deltaArray(int[] coeficientes) {
        // delta = ((b * b) - (4 * a * c))
        return ((coeficientes[1] * coeficientes[1]) - (4 * coeficientes[0] * coeficientes[2]));
    }

    public static double[] calcularRaizes(int[] coeficientes) {
        // realiza os cálculos
        double[] raizes = new double[2];
        double deltaTemp = deltaArray(coeficientes);
        raizes[0] = (-coeficientes[1] + Math.sqrt(deltaTemp)) / (2 * coeficientes[0]);
        raizes[1] = (-coeficientes[1] - Math.sqrt(deltaTemp)) / (2 * coeficientes[0]);
        return raizes;
    }

    public static void main(String[] args) {

        // int [] coeficientes = {1,3,5}; // para testar - sem raizes
        // int [] coeficientes = {1,4,4}; // para testar - 1 raiz, x = -2
        // int [] coeficientes = {1,5,4}; // para testar - 2 raizes, x1 = -1 e x2 = -4

        String[] incognita = { "A", "B", "C" };
        boolean forceExit = false;

        try {
            for (int i = 0; i < coeficientes.length; i++) {

                boolean controlCycle = true;
                do {
                    String temp = javax.swing.JOptionPane
                            .showInputDialog("Indique o coeficiente (" + incognita[i] + "): ");
                    if (temp == null || temp.equalsIgnoreCase("abortar")) {
                        // caso carreguem no botão 'cancel' ou queiram desistir escrevendo 'abortar'
                        forceExit = true;
                        throw new FormulaResolventeErrors(4);
                    } else if (validateInputN(temp)) {
                        // se for um integer válido
                        if (Integer.parseInt(temp) == 0 && i == 0) {
                            forceExit = true;
                            // verificar que o primeiro coeficiente, A, não é 0
                            throw new FormulaResolventeErrors(2);
                        } else {
                            // validação feita, gravar número e passar para o próximo
                            coeficientes[i] = Integer.parseInt(temp);
                            controlCycle = false;
                        }
                    }
                } while (controlCycle);
            }
        } catch (FormulaResolventeErrors e) {
            // apresentar erro personalizado
            javax.swing.JOptionPane.showMessageDialog(null, ERRORNOTE + e.getMessage());
        } catch (Exception e) {
            // erro geral - se houver
            javax.swing.JOptionPane.showMessageDialog(null, ERRORNOTE + e.getMessage());
            e.printStackTrace();
        }

        // para debuging
        /*
         * for (int i = 0; i < coeficientes.length; i++) {
         * System.out.println("O coeficiente " + incognita[i] + " é: " +
         * coeficientes[i]); }
         */

        if (!forceExit) {
            // se não foi saida abrupta, fazer cálculos
            double deltaResult = deltaArray(coeficientes);

            try {
                if (deltaResult < 0) {
                    // se não houver solução possível
                    throw new FormulaResolventeErrors(3);
                } else if (deltaResult == 0) {
                    // apenas uma solução possível
                    javax.swing.JOptionPane.showMessageDialog(null,
                            "O resultado da equação do segundo grau é " + calcularRaizes(coeficientes)[0] + " .");
                } else {
                    // cálculo com 2 soluções
                    javax.swing.JOptionPane.showMessageDialog(null, "O resultado da equação do segundo grau é "
                            + calcularRaizes(coeficientes)[0] + " e " + calcularRaizes(coeficientes)[1] + " .");
                }
            } catch (FormulaResolventeErrors e) {
            // apresentar erro personalizado
                javax.swing.JOptionPane.showMessageDialog(null, ERRORNOTE + e.getMessage());
            }
        } else {
            // adeus
            javax.swing.JOptionPane.showMessageDialog(null, "Bip Bip\nVolte sempre.");

        }

    }
}
