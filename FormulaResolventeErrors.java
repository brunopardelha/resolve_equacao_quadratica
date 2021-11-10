package pt.citeforma.java.c7;
// para tratar os erros da fórmula resolvente
// ver FormulaResolvente

public class FormulaResolventeErrors extends Exception {
    // ERROS A TRATAR:
    // resistente à introdução de números com formato inválido.
    // Nesse caso o programa deve avisar o utilizador que o formato do número não está correto e repetir a leitura do número. 
    // Caso o utilizador escreva “abortar” o programa deve terminar a sua execução.
    
    private static final long serialVersionUID = 1L;

    private static final String[] erro = { 
        /* 0 */ "Erro desconhecido.",
        /* 1 */ "O número inserido não é válido (entre -2147483648 e 2147483647).",
        /* 2 */ "Se o elemento 'A' é 0, a equação não é do segundo grau, logo não é preciso a fórmula resolvente.",
        /* 3 */ "A equação não tem zeros.",
        /* 4 */ "Decidiu abortar o programa.",
    };    

    public FormulaResolventeErrors() {
        // construtor da classe pai Exception
        super("Erro desconhecido");
    }
    
    public FormulaResolventeErrors(String s) {
        // construtor da classe pai Exception com string de argumento
        super(s);
    }

    public FormulaResolventeErrors(int n) {
        // usamos o if compacto para detetar referência fora dos limites do array
        this(erro[((n >= 0 && n < erro.length) ? n : 0)]);
    }
}
