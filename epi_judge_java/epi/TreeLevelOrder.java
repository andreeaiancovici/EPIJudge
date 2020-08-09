package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
public class TreeLevelOrder {

  @EpiTest(testDataFile = "tree_level_order.tsv")
  public static List<List<Integer>> binaryTreeDepthOrder(BinaryTreeNode<Integer> tree) {
    List<List<Integer>> results = new ArrayList<>();
    Deque<BinaryTreeNode<Integer>> queue = new LinkedList<>();
    queue.addLast(tree);
    while(!queue.isEmpty()) {
      List<Integer> result = new ArrayList<>();
      Deque<BinaryTreeNode<Integer>> nextQueue = new LinkedList<>();
      while(!queue.isEmpty()) {
         BinaryTreeNode<Integer> node = queue.pollFirst();
         if(node != null) {
           result.add(node.data);
           nextQueue.addLast(node.left);
           nextQueue.addLast(node.right);
         }
      }
      if(!result.isEmpty()) {
        results.add(result);
      }
      queue = nextQueue;
    }
    return results;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "TreeLevelOrder.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
