package Homework3;

import java.util.Stack;

public class BlockStack {
    int size;
    Stack<CodeBlock> codeBlockStack = new Stack<CodeBlock>();

    /** Pushes the given Codeblock onto a stack of codeBlocks
     *
     * @param block
     */
    public void push(CodeBlock block){
        codeBlockStack.push(block);
        size++;
    }

    /** Removes and returns the top codeBlock in a stack of CodeBlocks
     *
     * @return
     *      top codeBlock in a stack of CodeBlocks
     */
    public CodeBlock pop(){
        size--;
        return codeBlockStack.pop();
    }

    /** Returns the top element of a stack of Codeblocks
     *
     * @return
     */
    public CodeBlock peek(){
        return codeBlockStack.peek();
    }

    /** Returns a boolean value indicating if the stack of codeBlocks has any elements.
     *  True if there are no elements and false otherwise.
     *
     * @return
     *      boolean value indicating if the stack of codeBlocks has any elements.
     *      True if there are no elements and false otherwise.
     */
    public boolean isEmpty(){
        return codeBlockStack.isEmpty();
    }

    /** Returns the integer value indicating how many elements are in the stack of codeBlocks
     *
     * @return
     *      integer value indicating how many elements are in the stack of codeBlocks
     */
    public int size(){
        return codeBlockStack.size();
    }



}
