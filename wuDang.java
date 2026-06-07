package wuLingGongFu;

public class wuDang extends diZi{
    public wuDang(){
        super();
    }
    public wuDang(int x,int y,int neiLi,int wuYi,int HP){
        this.neiLi=neiLi;
        this.wuYi=wuYi;
        this.HP=HP;
        this.x=x;
        this.y=y;
    }
    @Override
    public int[] walk(){
        if(y==12){
            bushu=-1;
        }else if(y==1)bushu=1;
        y=y+bushu;
        //实现武当门派的移动
        position[0]=x;
        position[1]=y;
        return position;
    }
    public int fight(){
        return (int)((0.8*neiLi+0.2*wuYi)*(HP+10)/100);
    }
    public String print(){
        return "(" + x + "," + y + ")";
    }
}
