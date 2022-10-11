package Homework3;

import java.io.*;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Stack;

public class PythonTracer {
    public static final int SPACE_COUNT= 4;

    /** Traces through a python file that has a function and returns a complexity representing
     *  the time complexity of the python function
     * @param filename
     * @return
     * @throws FileNotFoundException
     * @throws IOException
     */
    public static Complexity traceFile(String filename) throws IOException {
        String version= "1";
        int subVersionCounter = 1;
        //Stack<CodeBlock> codeBlockStack = new Stack<CodeBlock>();
        BlockStack codeBlockStack = new BlockStack();
        File file = new File(filename);
        FileInputStream fis = new FileInputStream(file);
        Scanner scanner = new Scanner(fis);
        while(scanner.hasNext()){
            String strLine = scanner.nextLine();
            int indents=0;
            if(!strLine.isBlank() && !strLine.contains("#")){
                int spaceCount = strLine.indexOf(strLine.trim());
                indents = spaceCount/SPACE_COUNT;
                while(indents < codeBlockStack.size()){
                    if(indents == 0){
                        fis.close();
                        Complexity blockComplexity = codeBlockStack.peek().getBlockComplexity();
                        return blockComplexity;
                    } else{
                        CodeBlock leavingBlock = codeBlockStack.pop();
                        Complexity leavingBlockComplexity = leavingBlock.getBlockComplexity();
                        Complexity leavingBlockHighComplexity = leavingBlock.getHighestSubComplexity();
                        Complexity subComplexity = new Complexity();
                        subComplexity.setN_power(leavingBlockComplexity.getN_power()+leavingBlockHighComplexity.getN_power());
                        subComplexity.setLog_power(leavingBlockComplexity.getLog_power()+leavingBlockHighComplexity.getLog_power());

                        CodeBlock topBlock = codeBlockStack.peek();
                        Complexity currentHighComplexity = topBlock.getHighestSubComplexity();
                        if(subComplexity.isHigherOrderThan(currentHighComplexity)){
                            System.out.println("Leaving block "+leavingBlock.getName()+", updating block "+topBlock.getName()+":");
                            topBlock.setHighestSubComplexity(subComplexity);
                        } else{
                            System.out.println("Leaving block "+leavingBlock.getName()+", nothing to update");
                        }


                        String leavingBlockVersion = leavingBlock.getName();
                        int value = leavingBlockVersion.lastIndexOf(".");
                        version = version.substring(0, value+1);
                        version = version + String.valueOf(Integer.valueOf(leavingBlockVersion.substring(value+1))+1);
                        topBlock.toString();
                        System.out.println();
                    }
                }
                String[] tokens = strLine.trim().split("[ ]+");
                if(Arrays.asList(CodeBlock.BLOCK_TYPES).contains(tokens[0])){
                    CodeBlock codeBlock;
                    String keyword = tokens[0];

                    if(keyword.equals("for")){
                        Complexity complexity = null;
                        codeBlock = new CodeBlock();
                        codeBlock.setName(version);
                        if(tokens[tokens.length-1].equals("N:")){
                            complexity = new Complexity(1, 0);
                        } else if(tokens[tokens.length-1].equals("log_N:")){
                            complexity = new Complexity(0, 1);
                        }
                        codeBlock.setBlockComplexity(complexity);
                        codeBlock.setHighestSubComplexity(new Complexity());
                        System.out.println("Entering block "+codeBlock.getName()+ " 'for':");
                        codeBlock.toString();
                        System.out.println();
                        codeBlockStack.push(codeBlock);

                    } else if(keyword.equals("while")){
                        codeBlock = new CodeBlock();
                        codeBlock.setName(version);
                        Complexity complexity = new Complexity(0, 0);
                        codeBlock.setBlockComplexity(complexity);
                        codeBlock.setHighestSubComplexity(new Complexity());
                        codeBlock.setLoopVariable(tokens[1]);
                        System.out.println("Entering block "+codeBlock.getName()+ " 'while':");
                        codeBlock.toString();
                        System.out.println();
                        codeBlockStack.push(codeBlock);

                    } else if(keyword.equals("def")){
                        codeBlock = new CodeBlock();
                        codeBlock.setName(version);
                        Complexity complexity = new Complexity(0, 0);
                        codeBlock.setBlockComplexity(complexity);
                        codeBlock.setHighestSubComplexity(new Complexity());
                        System.out.println("Entering block "+codeBlock.getName()+ " 'def':");
                        codeBlock.toString();
                        System.out.println();
                        codeBlockStack.push(codeBlock);
                    }
                    version = version+".1";
                } else if(codeBlockStack.peek().getLoopVariable() != null && Arrays.asList(tokens).contains(codeBlockStack.peek().getLoopVariable())){
                    String loopVariable = codeBlockStack.peek().getLoopVariable();
                    System.out.println("Found update statement, updating block "+codeBlockStack.peek().getName()+":");
                    if(tokens[0].equals(loopVariable)){
                        if(tokens[1].equals("-=")){
                            codeBlockStack.peek().setBlockComplexity(new Complexity(1, 0));
                        } else if(tokens[1].equals("/=") && tokens[2].equals("2")){
                            codeBlockStack.peek().setBlockComplexity(new Complexity(0, 1));
                        }

                    }
                    codeBlockStack.peek().toString();
                    System.out.println();
                }
            } else{
                // Ignore comment/blank line
            }
        }
        while(codeBlockStack.size()>1){
            CodeBlock oldTop = codeBlockStack.pop();
            Complexity oldTopHighestComplexity = oldTop.getHighestSubComplexity();
            Complexity thisSubComplexity = codeBlockStack.peek().getHighestSubComplexity();
            if(oldTopHighestComplexity.isHigherOrderThan(thisSubComplexity)){
                codeBlockStack.peek().setHighestSubComplexity(oldTopHighestComplexity);
            }

        }
        System.out.println("Leaving Block 1.");
        System.out.println();
        return codeBlockStack.pop().getHighestSubComplexity();
    }

    /** Main method
     *
     * @param args
     */
    public static void main(String[] args){
        boolean isDone = false;
        while (!isDone) {
            Complexity totalComplexity;
            Scanner sc  = new Scanner(System.in);
            System.out.print("Please enter a file name (or 'quit' to quit):");
            String input = sc.next().trim();
            if(input.equalsIgnoreCase("quit")){
                System.out.println("Program terminating successfully...");
                isDone = true;
                continue;
            } else if(input.endsWith(".py")){
                try {
                    totalComplexity = traceFile(input);
                    String printTotalComplexity = totalComplexity.toString();
                    System.out.println("Overall complexity of test_function: "+printTotalComplexity);
                } catch (IOException e) {
                    System.out.println("File was not found. Try Again.");
                }
            } else{
                System.out.println("Not a valid file");
            }
        }
    }



}
