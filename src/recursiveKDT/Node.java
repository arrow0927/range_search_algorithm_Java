package recursiveKDT;

import static recursiveKDT.Utility.*;

public class Node
{
  private int id;
  private int x;
  private int y;
  private Node lc = null;
  private Node rc = null;
  private Node parent = null;

  public Node(int _i, int _x, int _y)
  {
    this.id = _i;
    this.x = _x;
    this.y = _y;
  }

  public int getX()
  {
    return this.x;
  }
  public int getY()
  {
    return this.y;
  }

  public int getId()
  {
    return this.id;
  }

  public Node getLc()
  {
    return lc;
  }

  public void setLc(Node lc)
  {
    this.lc = lc;
    if (lc != null)
      this.lc.setParent(this);
  }

  public Node getRc()
  {
    return rc;
  }

  public void setRc(Node rc)
  {
    this.rc = rc;
    if (rc != null)
      this.rc.setParent(this);
  }

  public String toString()
  {
    return "[" + "id:" + this.id + " (" + this.x + "," + this.y + ")] ";
  }

  public boolean equals(Node n)
  {
    if ((this.x == n.x) && (this.y == n.y))
      return true;
    else
      return false;
  }

  public Node getParent()
  {
    return parent;
  }

  public void setParent(Node parent)
  {
    this.parent = parent;
  }

  public int coord(boolean c)
  {
    return c ? x : y;
  }

  public IPoint2 pt()
  {
    return new IPoint2(x, y);
  }

}
