//**************************** Copyright © Jonathon Bell. All rights reserved.
//*
//*
//*  Version : Header:
//*
//*
//*  Purpose :
//*
//*
//*  Comments: This file uses a tab size of 2 spaces.
//*
//*
//****************************************************************************

package com.wolery
package why

import com.wolery.fx.dock.{ DockNode, DockPane, DockPos }
import com.wolery.fx.util.Application

import javafx.scene.Scene
import javafx.scene.control.{ TreeItem, TreeView }
import javafx.scene.image.{ Image, ImageView }
import javafx.stage.Stage

//****************************************************************************

object Main extends Application
{
  def start(stage: Stage): Unit =
  {
    val pane = new DockPane()
    val icon = new Image(getClass.getResource("/docknode.png").toExternalForm)

    def node(name:String,position: DockPos): DockNode =
    {
      val t =  new TreeView(new TreeItem("root"))
      val dn = new DockNode(t,name,new ImageView(icon))
      dn.setPrefSize(100,100)
      dn.dock(pane,position)
      dn
    }

    val tDock = node("Top",   DockPos.TOP)
    val bDock = node("Bottom",DockPos.BOTTOM)
    val lDock = node("Left",  DockPos.LEFT)
    val rDock = node("Right", DockPos.RIGHT)

 // disable bottom node from being undocked
    bDock.setDockTitleBar(null)

    stage.setScene(new Scene(pane,800,500))
    stage.setTitle("Docking Test")
    stage.sizeToScene()
    stage.show()

    DockPane.initializeDefaultUserAgentStylesheet()
  }
}

//****************************************************************************
