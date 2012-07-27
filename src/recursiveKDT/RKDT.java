package recursiveKDT;

import static recursiveKDT.Utility.*;
import java.util.*;

public class RKDT
{
  Node root = null;
  private static final boolean db = false;

  public RKDT()
  {
  }

  public void buildTree(ArrayList<Node> list1)
  {
    pr("building tree with ArrayList of nodes of size = " + list1.size());
    this.root = null;
    int depth = 0;
    int lastIndex = list1.size();
    int beginIndex = 0;
    this.root = this.buildTreehelper(list1, depth, beginIndex, lastIndex);
  }

  //_List must be at least of size 1 , beginIndex <= endIndex, _depth >=0
  private Node buildTreehelper(List<Node> _List, int _depth, int beginIndex,
      int endIndex2)
  {
    Node medianNode = null;

    /*Case: Error */
    if (beginIndex < endIndex2)
    {
      /*find median that splits the list
      *first we need to sort the list based on the dimension
      *if depth is even dimension ==X or dimension ==Y
      */
      boolean depthIsEven = (_depth % 2 == 0);
      Comparator<Node> c = depthIsEven ? NodeComparator.XCOMPARE
          : NodeComparator.YCOMPARE;
      Collections.sort(_List.subList(beginIndex, endIndex2), c);

      int indexOfMedian = (beginIndex + endIndex2) / 2;

      medianNode = _List.get(indexOfMedian);

      medianNode.setLc(buildTreehelper(_List, _depth + 1, beginIndex,
          indexOfMedian));

      medianNode.setRc(buildTreehelper(_List, _depth + 1, indexOfMedian + 1,
          endIndex2));

    }
    return medianNode;
  }
  /*See utility class for query region*/
  public ArrayList<Node> getPointsInRange(IRect queryRect)
  {
    ArrayList<Node> tmpArray = new ArrayList<Node>(0);
    int depth = 0;
    searchTreeForMemberPoints(root, depth, tmpArray, queryRect);
    return tmpArray;

  }

  private void searchTreeForMemberPoints(Node node, int depth,
      ArrayList<Node> arr, IRect q)
  {

    final boolean db = false;

    if (node == null)
      return;

    if (db)
      pr("searchTree depth=" + depth + " q=" + q + " node=" + node.pt());

    if (q.contains(node.getX(), node.getY()))
    {
      if (db)
        pr(" contains point, adding");

      arr.add(node);
    }

    if (isLeafNode(node))
      return;

    // we've parameterized the IPoint, IRect, and RKDTNode classes to
    // allow referring to x or y coordinates by boolean true and false respectively.
    boolean c = ((depth & 1) == 0);

    if (q.min(c) <= node.coord(c)) //if the x/y value of query reg <= cooresponding x/y val of node
    {
      if (db)
        pr("searching left child");
      searchTreeForMemberPoints(node.getLc(), depth + 1, arr, q);
    }
    if (q.max(c) >= node.coord(c))
    {
      if (db)
        pr("searching right child");
      searchTreeForMemberPoints(node.getRc(), depth + 1, arr, q);
    }
 
  }
  private boolean isLeafNode(Node n)
  {
    return ((n.getLc() == null) && (n.getRc() == null)) ? true : false;
  }

  
  //===============================================================================
  private static class NodeComparator implements Comparator<Node>
  {

    public static final Comparator<Node> XCOMPARE = new NodeComparator(true);
    public static final Comparator<Node> YCOMPARE = new NodeComparator(false);
    private NodeComparator(boolean even)
    {
      this.even = even;
    }
    private boolean even;
    @Override
    public int compare(Node o1, Node o2)
    {
      final boolean db = false;

      int ret;
      if (even)
        ret = o1.getX() - o2.getX();
      else
        ret = o1.getY() - o2.getY();
      if (ret == 0)
        ret = o1.getId() - o2.getId();

      if (db)
        pr("\n\n compare " + o1 + " with " + o2 + " yields  " + ret);
      return ret;
    }
  }

}
