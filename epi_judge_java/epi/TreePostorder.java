package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.*;

public class TreePostorder {
  @EpiTest(testDataFile = "tree_postorder.tsv")

  // We use stack and previous node pointer to simulate postorder traversal.
  public static List<Integer> postorderTraversal(BinaryTreeNode<Integer> tree) {
    List<Integer> postOrder = new ArrayList<>();
    if (tree != null) {
      Deque<BinaryTreeNode<Integer>> stack = new LinkedList<>();
      BinaryTreeNode<Integer> node = tree;
      while(!stack.isEmpty() || node != null) {
        if(node != null) {
          stack.push(node);
          node = node.left;
        } else {
          node = stack.pop();
          if(node.right == null) {
            postOrder.add(node.data);
          } else {
            stack.push(node);
          }
          node = node.right;
        }
      }
    }
    return postOrder;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "TreePostorder.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
