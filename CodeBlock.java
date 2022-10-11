package Homework3;

import java.util.Arrays;

public class CodeBlock {
    static final String[] BLOCK_TYPES = {"def", "for", "while", "if", "else", "elif"};
    static int DEF = 0;
    static int FOR = 1;
    static int WHILE = 2;
    static int IF = 3;
    static int ELIF = 4;
    static int ELSE = 5;


    private Complexity blockComplexity;
    private Complexity highestSubComplexity;
    private String name;
    private String loopVariable;


    /** Makes an empty instance of CodeBlock
     *
     */
    public CodeBlock() {
    }

    /** Returns the name of the codeblock that calls this method.
     *
     * @return
     *      Name of the CodeBlock that calls this method.
     */
    public String getName() {
        return name;
    }

    /** Sets the name of the CodeBlock that calls this method to the parameter value
     *
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /** Returns the complexity object of the codeblock that calls this method
     *
     * @return
     *      Complexity object of the CodeBlock that calls this method
     */
    public Complexity getBlockComplexity() {
        return blockComplexity;
    }

    /** Sets the complexity object of the codeblock that calls this method to the
     * complexity object passed as the parameter value
     *
     * @param blockComplexity
     */
    public void setBlockComplexity(Complexity blockComplexity) {
        this.blockComplexity = blockComplexity;
    }

    /** Returns the complexity object indicting the highest sub complexity of the
     *  codeblock that calls this method
     *
     * @return
     *      complexity object indicting the highest sub complexity of the
     *      odeblock that calls this method
     */
    public Complexity getHighestSubComplexity() {
        return highestSubComplexity;
    }

    /** Sets the highest sub-complexity value of this CodeBlock object to the Complexity object passed as the parameter
     *
     * @param highestSubComplexity
     */
    public void setHighestSubComplexity(Complexity highestSubComplexity) {
        this.highestSubComplexity = highestSubComplexity;
    }

    /** Returns the loop variable value of the object that calls this method
     *
     * @return
     *      loop variable value of the object that calls this method
     */
    public String getLoopVariable() {
        return loopVariable;
    }

    /** Sets the loopvariable of the object that calls this method to the value passed as the parameter
     *
     * @param loopVariable
     */
    public void setLoopVariable(String loopVariable) {
        this.loopVariable = loopVariable;
    }

    /** Returns a properly formatted string representation of the codeblock object that calls this method
     *
     * @return
     */
    @Override
    public String toString() {
        String blockComplexity = getBlockComplexity().toString();
        String highestSubComplexity = getHighestSubComplexity().toString();
        System.out.println("    BLOCK " + name + ":       block complexity =  "+ blockComplexity +"       highest sub-complexity = "+ highestSubComplexity );
        return null;
    }

    /** Returns null if the value passes in the parameter does not match with any of
     *  values in the array of keyword(BLOCK_TYPES) and returns the first word in
     *  the line otherwise
     *
     * @param line
     * @return
     *       * null if the value passes in the parameter does not match with any of
     *      values in the array of keyword(BLOCK_TYPES)
     *       * the first word in the line if it matches with any elements in BLOCK_TYPES
     *
     */
    public static String containsKeyword(String line){
        String keyword = null;
        String[] eachWord = line.split(" ");
        //String[] eachWord = line.trim().split("[ ]+");
        String tempKeyword = eachWord[0];
        for(String blockType: BLOCK_TYPES){
            if(blockType.equals(tempKeyword)){
                keyword = blockType;
            }
        }
        return keyword;
    }
}
