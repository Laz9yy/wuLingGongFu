package wuLingGongFu;

public class shaoLin extends diZi{
    public shaoLin(){
        super();
    }
    public shaoLin(int x,int y,int neiLi,int wuYi,int HP){
        this.neiLi=neiLi;
        this.wuYi=wuYi;
        this.HP=HP;
        this.x=x;
        this.y=y;
    }
    @Override
    public int[] walk(){
        if(x==12){
            bushu=-1;
        }else if(x==1)bushu=1;//控制方向向量
        x=x+bushu;
        //实现少林门派的移动
        position[0]=x;
        position[1]=y;
        return position;
    }
    public int fight(){
        return (int)((0.5*neiLi+0.5*wuYi)*(HP+10)/100);
    }
    public String print(){
            return "(" + x + "," + y + ")";
    }
}
