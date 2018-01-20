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

import owl.gui.util._
import javafx.stage.Stage
import owl.gui.ConsoleView

//****************************************************************************

object Main extends Application
{
  def start(stage: Stage): Unit =
  {
    ConsoleView(stage)
  }

  override
  def stop() =
  {
  }
}

//****************************************************************************
