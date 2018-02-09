//**************************** Copyright © Jonathon Bell. All rights reserved.
//*
//*
//*  Version : Header:
//*
//*
//*  Purpose : A disposable javafx desktop application for testing out ideas.
//*
//*
//*  Comments: This file uses a tab size of 2 spaces.
//*
//*
//****************************************************************************

package com.wolery
package why

import javafx.scene.{Group,Scene}
import javafx.scene.shape.Circle
import javafx.stage.Stage

//****************************************************************************

object Main extends fx.util.Application
{
  def start(stage: Stage): Unit =
  {
     stage.setScene(new Scene(new Group(new Circle(40,40,30)),400,300))
     stage.setTitle("My Application")
     stage.show()
  }
}

//****************************************************************************
