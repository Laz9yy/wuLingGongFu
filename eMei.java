package wuLingGongFu;

public class eMei extends diZi{
    private boolean isBoundary=true;
    public eMei(){
        super();
    }
    public eMei(int x,int y,int neiLi,int wuYi,int HP){
        this.neiLi=neiLi;
        this.wuYi=wuYi;
        this.HP=HP;
        this.x=x;
        this.y=y;
    }
     @Override
    public int[] walk(){

        // 特殊点：(1,12)和(12,1) 直接返回当前坐标，不移动
        if((x==1&&y==12)||(x==12&&y==1)){
            position[0]=x;
            position[1]=y;
            return position;
        }   

        // 到达右下边界 切换向左上移动
        if(x==12||y==12){
            isBoundary=false;
        }

        // 到达左上边界 切换向右下移动
        if(x==1||y==1){
            isBoundary=true;
        }

        // 向右下移动
        if(isBoundary){
            x++;
            y++;
        }
        // 向左上移动
        else{
            x--;
            y--;
        }

        position[0]=x;
        position[1]=y;

        return position;
    }
    public int fight(){
        return (int)((0.2*neiLi+0.8*wuYi)*(HP+10)/100);
    }
    public String print(){
            return "(" + x + "," + y + ")";
    }
}
