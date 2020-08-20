package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TimedExecutor;
import java.util.ArrayList;
import java.util.List;
public class TreeFromPreorderWithNull {
  private static int index;

  public static BinaryTreeNode<Integer>
  reconstructPreorder(List<Integer> preorder) {
    index = 0;
    return buildTree(preorder);
  }

  private static BinaryTreeNode<Integer> buildTree(List<Integer> preorder) {
    Integer data = preorder.get(index);
    if(data == null) {
      return null;
    }
    BinaryTreeNode<Integer> tree = new BinaryTreeNode<>(data);
    index++;
    tree.left = buildTree(preorder);
    index++;
    tree.right = buildTree(preorder);
    return tree;
  }

  @EpiTest(testDataFile = "tree_from_preorder_with_null.tsv")
  public static BinaryTreeNode<Integer>
  reconstructPreorderWrapper(TimedExecutor executor, List<String> strings)
      throws Exception {
    List<Integer> ints = new ArrayList<>();
    for (String s : strings) {
      if (s.equals("null")) {
        ints.add(null);
      } else {
        ints.add(Integer.parseInt(s));
      }
    }

    return executor.run(() -> reconstructPreorder(ints));
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "TreeFromPreorderWithNull.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
