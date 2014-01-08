package recursiveKDT;

public class IPoint2
{
  public int x, y;
  public IPoint2()
  {
  }
  public IPoint2(int x, int y)
  {
    this.x = x;
    this.y = y;
  }
  public String toString()
  {
    return "(" + x + " " + y + ")";
  }
  public int coord(boolean c)
  {
    return c ? x : y;
  }
}
