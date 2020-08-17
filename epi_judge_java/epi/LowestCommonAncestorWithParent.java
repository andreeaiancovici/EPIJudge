package epi;
import epi.test_framework.BinaryTreeUtils;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TestFailure;
import epi.test_framework.TimedExecutor;
public class LowestCommonAncestorWithParent {

  public static BinaryTree<Integer> LCA(BinaryTree<Integer> node0,
                                        BinaryTree<Integer> node1) {
    int depth0 = getDepth(node0);
    int depth1 = getDepth(node1);
    if (depth0 < depth1) {
      while (depth0 < depth1) {
        node1 = node1.parent;
        depth1--;
      }
    } else if (depth0 > depth1) {
      while (depth0 > depth1) {
        node0 = node0.parent;
        depth0--;
      }
    }
    while(node0 != node1) {
      node0 = node0.parent;
      node1 = node1.parent;
    }
    return node0;
  }

  private static int getDepth(BinaryTree<Integer> node) {
    if(node.parent == null) {
      return 0;
    } else {
      return getDepth(node.parent) + 1;
    }
  }

  @EpiTest(testDataFile = "lowest_common_ancestor.tsv")
  public static int lcaWrapper(TimedExecutor executor, BinaryTree<Integer> tree,
                               Integer key0, Integer key1) throws Exception {
    BinaryTree<Integer> node0 = BinaryTreeUtils.mustFindNode(tree, key0);
    BinaryTree<Integer> node1 = BinaryTreeUtils.mustFindNode(tree, key1);

    BinaryTree<Integer> result = executor.run(() -> LCA(node0, node1));

    if (result == null) {
      throw new TestFailure("Result can not be null");
    }
    return result.data;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "LowestCommonAncestorWithParent.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
