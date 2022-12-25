package com.example.demo;

import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.shape.Circle;

import java.util.EnumSet;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Player{
    private static final double Y_STEP = 2.0D;
    private static final double X_STEP = 2.0D;

    private static final double MAX_JUMP = 20.0D;


    private Circle circle = new Circle();
    private Set<MoveStatusEnum> status;

    private double jumpStatus = 0.0D;

    Set<MoveStatusEnum> getStatus()
    {
        return this.status;
    }

    public Player() {
        this(10,0,0);
    }

    public Player(double height, double centerX, double centerY)
    {
        this(height,centerX,centerY,null);
    }

    public Player(double height, double centerX, double centerY, Group node )
    {
        this.circle.setRadius(height/2);
        this.circle.setCenterX(centerX);
        this.circle.setCenterY(centerY);
        addToStage(node);
    }

    public void addStatus(MoveStatusEnum statusEnum)
    {

        if (status == null)
        {
            status = new HashSet<>();
        }

        if ((status.contains(MoveStatusEnum.DOWN)) && (statusEnum.equals(MoveStatusEnum.UP)))
        {
            return;
        }
        if ((status.contains(MoveStatusEnum.LEFT)) && (statusEnum.equals(MoveStatusEnum.RIGHT)))
        {
            removeStatus(MoveStatusEnum.LEFT);
            return;
        }
        status.add(statusEnum);
    }

    public void removeStatus(MoveStatusEnum moveStatusEnum)
    {
        try {
            status.remove(moveStatusEnum);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public void removeAllStatus(){
        status = null;
    }


    public void addToStage(Group group)
    {
        if (group != null) {
            group.getChildren().add(circle);
        }
    }


    public void move()
    {
        if ((status != null) && (!status.isEmpty()))
        {
            status.forEach(
                    (status)->{

                      switch (status)
                      {
                          case UP:
                              if (jumpStatus+Y_STEP < MAX_JUMP) {
                                  jumpStatus+=Y_STEP;
                                  this.circle.setCenterY(this.circle.getCenterY() - Y_STEP);
                              }
                              else
                              {
                                  removeStatus(MoveStatusEnum.UP);
                                  jumpStatus = 0.0D;
                                  addStatus(MoveStatusEnum.DOWN);
                              }
                              break;
                          case DOWN:
                              this.circle.setCenterY(this.circle.getCenterY()+Y_STEP);
                              break;
                          case LEFT:
                              this.circle.setCenterX(this.circle.getCenterX()-X_STEP);
                              break;
                          case RIGHT:
                              this.circle.setCenterX(this.circle.getCenterX()+X_STEP);
                              break;
                      }
                    }
            );
        }
    }


}
