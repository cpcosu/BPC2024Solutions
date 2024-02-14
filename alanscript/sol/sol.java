import java.util.*;

public class sol {

    enum StatementType {
        SWAP,
        IF,
        WHILE
    }

    static class SwapStatement {
        String variable;

        @Override
        public String toString() {
            return "SwapStatement{" +
                    "variable='" + variable + '\'' +
                    '}';
        }
    }

    static class IfStatement {
        String variable;
        List<Statement> thenStatements;
        List<Statement> elseStatements;

        @Override
        public String toString() {
            return "IfStatement{" +
                    "variable='" + variable + '\'' +
                    ", thenStatements=" + thenStatements +
                    ", elseStatements=" + elseStatements +
                    '}';
        }
    }

    static class WhileStatement {
        String variable;
        List<Statement> statements;

        @Override
        public String toString() {
            return "WhileStatement{" +
                    "variable='" + variable + '\'' +
                    ", statements=" + statements +
                    '}';
        }
    }

    static class Statement {
        StatementType type;

        /* Exactly one of these is not null, depending on the value of type. */
        SwapStatement swapStatement;
        IfStatement ifStatement;
        WhileStatement whileStatement;

        static Statement newSwap(String variable) {
            Statement statement = new Statement();
            statement.type = StatementType.SWAP;
            SwapStatement swapStatement = new SwapStatement();
            swapStatement.variable = variable;
            statement.swapStatement = swapStatement;
            return statement;
        }

        static Statement newIf(String variable, List<Statement> thenStatements, List<Statement> elseStatements) {
            Statement statement = new Statement();
            statement.type = StatementType.IF;
            IfStatement ifStatement = new IfStatement();
            ifStatement.variable = variable;
            ifStatement.thenStatements = thenStatements;
            ifStatement.elseStatements = elseStatements;
            statement.ifStatement = ifStatement;
            return statement;
        }

        static Statement newWhile(String variable, List<Statement> statements) {
            Statement statement = new Statement();
            statement.type = StatementType.WHILE;
            WhileStatement whileStatement = new WhileStatement();
            whileStatement.variable = variable;
            whileStatement.statements = statements;
            statement.whileStatement = whileStatement;
            return statement;
        }

        @Override
        public String toString() {
            switch (type) {
                case SWAP: {
                    return swapStatement.toString();
                }
                case IF: {
                    return ifStatement.toString();
                }
                case WHILE: {
                    return whileStatement.toString();
                }
                default:
                    throw new RuntimeException("b");
            }
        }
    }

    static Statement parse(List<String> tokens) {
        String first = tokens.remove(0);
        switch (first) {
            case "swap": {
                String variable = tokens.remove(0);
                return Statement.newSwap(variable);
            }
            case "if": {
                String variable = tokens.remove(0);
                tokens.remove(0); // skip "do"
                List<Statement> thenStatements = new ArrayList<>();
                while (!tokens.get(0).equals("else")) {
                    thenStatements.add(parse(tokens));
                }
                tokens.remove(0); // skip "else"
                List<Statement> elseStatements = new ArrayList<>();
                while (!tokens.get(0).equals("end")) {
                    elseStatements.add(parse(tokens));
                }
                tokens.remove(0); // skip "end"
                return Statement.newIf(variable, thenStatements, elseStatements);
            }
            case "while": {
                String variable = tokens.remove(0);
                tokens.remove(0); // skip "do"
                List<Statement> statements = new ArrayList<>();
                while (!tokens.get(0).equals("end")) {
                    statements.add(parse(tokens));
                }
                tokens.remove(0); // skip "end"
                return Statement.newWhile(variable, statements);
            }
            default: {
                throw new RuntimeException("WUH WOH. input is invalid.");
            }
        }
    }

    static List<Statement> parseAll(List<String> tokens) {
        List<Statement> statements = new ArrayList<>();
        while (tokens.size() > 0) {
            statements.add(parse(tokens));
        }
        return statements;
    }

    static int getBit(int x, int bit) {
        return (x >> bit) & 1;
    }

    static int swapBit(int x, int bit) {
        return x ^ (1 << bit);
    }

    static int run(Statement statement, int state) {
        switch (statement.type) {
            case SWAP: {
                int v = statement.swapStatement.variable.charAt(0) - 'a';
                return swapBit(state, v);
            }
            case IF: {
                int v = statement.ifStatement.variable.charAt(0) - 'a';
                int newState = state;
                if (getBit(newState, v) == 1) {
                    for (Statement subStatement : statement.ifStatement.thenStatements) {
                        newState = run(subStatement, newState);
                    }
                } else {
                    for (Statement subStatement : statement.ifStatement.elseStatements) {
                        newState = run(subStatement, newState);
                    }
                }
                return newState;
            }
            case WHILE: {
                int v = statement.whileStatement.variable.charAt(0) - 'a';
                int newState = state;
                Set<Integer> alreadyStates = new HashSet<>();
                alreadyStates.add(newState);
                while (getBit(newState, v) == 1) {
                    for (Statement subStatement : statement.whileStatement.statements) {
                        newState = run(subStatement, newState);
                    }
                    if (alreadyStates.contains(newState)) {
                        System.out.println("doesn't halt");
                        System.exit(0);
                    }
                    alreadyStates.add(newState);
                }
                return newState;
            }
            default: {
                throw new RuntimeException("oh noes! something is broken");
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int numLines = scanner.nextInt();
        scanner.nextLine();

        List<String> tokens = new ArrayList<>();
        for (int i = 0; i < numLines; i++) {
            for (String token : scanner.nextLine().split(" +")) {
                if (token.length() > 0) {
                    tokens.add(token);
                }
            }
        }

        List<Statement> statements = parseAll(tokens);

        int state = 0;

        for (Statement statement : statements) {
            state = run(statement, state);
        }

        // System.out.println("0b" + Integer.toString(state, 2));
        System.out.println("halts");
    }
}
