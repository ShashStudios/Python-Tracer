package Homework3;

public class Complexity {
    int n_power;
    int log_power;

    /** Makes a complexity object
     *
     * @param n_power
     * @param log_power
     */
    public Complexity(int n_power, int log_power) {
        this.n_power = n_power;
        this.log_power = log_power;
    }

    /** Makes a blank complexity object
     *
     */
    public Complexity() {
    }

    /** Returns the n power of the complexity object
     *
     * @return
     *      n power of the complexity object
     */
    public int getN_power() {
        return n_power;
    }

    /** Sets the n power of the complexity object that calls the method
     *  to the value passed
     *
     * @param n_power
     */
    public void setN_power(int n_power) {
        this.n_power = n_power;
    }

    /** Returns the log power value of the Complexity object that calls this method
     *
     *
     * @return
     *      log power value of the Complexity object that calls this method
     */
    public int getLog_power() {
        return log_power;
    }

    /** Sets the log power of the object that calls this to the param value
     *
     * @param log_power
     */
    public void setLog_power(int log_power) {
        this.log_power = log_power;
    }

    /** Returns a properly formatted string representation of the Complexity object
     *
     * @return
     */
    @Override
    public String toString() {
        String complexity="";
        if(this.n_power==0 && this.log_power==0){
            complexity = "O(1)";
        } else if(this.n_power==1 && this.log_power==0){
            complexity = "O(n)";
        } else if(this.n_power==1 && this.log_power>0){
            complexity = "O(n * log(n))";
        }else if(this.n_power>1 && this.log_power==0){
            complexity = "O(n^"+this.n_power +")";
        } else if(this.n_power==0 && this.log_power>0){
            complexity = "O(log(n))";
        } else if(this.n_power>0 && this.log_power>0){
            complexity = "O(n^"+this.n_power+" * log(n))";
        }
        return complexity;
    }

    /** Returns a boolean value indicating if the Complexity of this value is
     *  of a higher order than the Complexoty of the parameter.
     *
     *
     * @param complexity
     * @return
     *      boolean value indicating if the Complexity of this value is
     *      of a higher order than the Complexity of the parameter.
     */
    public boolean isHigherOrderThan(Complexity complexity){
        return(this.n_power > complexity.n_power || this.log_power > complexity.log_power);
    }

    /** Returns the time complexity of a for block in a python function
     *
     * @param data
     * @return
     *      Returns the time complexity of a for block in a python function
     */
    public static CodeBlock forComplexity(String data){
        CodeBlock codeBlock = new CodeBlock();
        Complexity complexity = new Complexity();
        complexity.setN_power(0);
        if(data.contains(" N")){
            complexity.setN_power(1);
        } else if(data.contains(" log")){
            complexity.setLog_power(2);
        }
        codeBlock.setBlockComplexity(complexity);
        return codeBlock;
    }

    /** Returns the time complexity of a while block in a python function
     *
     * @param data
     * @return
     *      Returns the time complexity of a while block in a python function
     */
    public static CodeBlock whileComplexity(String data) {
        CodeBlock codeBlock = new CodeBlock();
        int indexOfWhile = data.indexOf(":");
        codeBlock.setLoopVariable(String.valueOf(data.charAt(indexOfWhile-1)));
        codeBlock.setBlockComplexity(new Complexity());
        return codeBlock;
    }

}
