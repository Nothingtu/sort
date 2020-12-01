package sort1110;

import java.util.ArrayList;
import java.util.List;

/**
 * 图的节点
 */
public class MapNode {

    //创建索引方便后期的找寻
    public int index;
    public String val;
    public List<MapNode> neighborNode;

    public MapNode(int index,String val){
        this.index = index;
        this.val = val;
        neighborNode = new ArrayList<>();
    }

}
