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

import java.io.IOException
import java.nio.file.{Files, Paths}
import java.util.Random

import fx.dock._

import javafx.scene.Scene
import javafx.scene.control.{Tab, TabPane, TableColumn, TableView, TreeItem, TreeView}
import javafx.scene.image.{Image, ImageView}
import javafx.scene.web.HTMLEditor
import javafx.stage.Stage
import com.wolery.owl.gui.util.Application

//****************************************************************************

object Main extends Application
{
  def start(primaryStage: Stage): Unit =
  {
    primaryStage.setTitle("DockFX")

    // create a dock pane that will manage our dock nodes and handle the layout
    val dockPane   = new DockPane()

    // create a default test node for the center of the dock area
    val tabs       = new TabPane()
    val htmlEditor = new HTMLEditor()

    try
    {
      htmlEditor.setHtmlText(new String(Files.readAllBytes(Paths.get("readme.html"))))
    }
    catch
    {
      case e: IOException ⇒ e.printStackTrace()
    }

    // empty tabs ensure that dock node has its own background color when floating
    tabs.getTabs.addAll(new Tab("Tab 1",htmlEditor),
                        new Tab("Tab 2"),
                        new Tab("Tab 3"))

    val tableView = new TableView[String]()

    tableView.getColumns.addAll(new TableColumn[String,String]("A"),
                                new TableColumn[String,String]("B"))

    // load an image to caption the dock nodes
    val dockImage = new Image(getClass.getResource("/docknode.png").toExternalForm())

    // create and dock some prototype dock nodes to the middle of the dock pane
    // the preferred sizes are used to specify the relative size of the node
    // to the other nodes

    // we can use this to give our central content a larger area where
    // the top and bottom nodes have a preferred width of 300 which means that
    // when a node is docked relative to them such as the left or right dock below
    // they will have 300 / 100 + 300 (400) or 75% of their previous width
    // after both the left and right node's are docked the center docks end up with 50% of the width

    val tabsDock = new DockNode(tabs, "Tabs Dock", new ImageView(dockImage))
    tabsDock.setPrefSize(300, 100)
    tabsDock.dock(dockPane, DockPos.TOP)

    val tableDock = new DockNode(tableView)
    // let's disable our table from being undocked
    tableDock.setDockTitleBar(null)
    tableDock.setPrefSize(300, 100)
    tableDock.dock(dockPane, DockPos.BOTTOM)

    primaryStage.setScene(new Scene(dockPane, 800, 500))
    primaryStage.sizeToScene()

    primaryStage.show()

    // can be created and docked before or after the scene is created
    // and the stage is shown
    var treeDock = new DockNode(generateRandomTree(), "Tree Dock", new ImageView(dockImage))
    treeDock.setPrefSize(100, 100)
    treeDock.dock(dockPane, DockPos.LEFT)
    treeDock = new DockNode(generateRandomTree(), "Tree Dock", new ImageView(dockImage))
    treeDock.setPrefSize(100, 100)
    treeDock.dock(dockPane, DockPos.RIGHT)

    // test the look and feel with both Caspian and Modena
    // Application.setUserAgentStylesheet("MODENA")
    // initialize the default styles for the dock pane and undocked nodes using the DockFX
    // library's internal Default.css stylesheet
    // unlike other custom control libraries this allows the user to override them globally
    // using the style manager just as they can with internal JavaFX controls
    // this must be called after the primary stage is shown
    // https://bugs.openjdk.java.net/browse/JDK-8132900
    DockPane.initializeDefaultUserAgentStylesheet()
  }

  private def generateRandomTree(): TreeView[String] =
    {
      // create a demonstration tree view to use as the contents for a dock node
      val root = new TreeItem[String]("Root")
      val treeView = new TreeView[String](root)
      treeView.setShowRoot(false)

      // populate the prototype tree with some random nodes
      val rand = new Random()

      for (i ← 4 + rand.nextInt(8) to 0 by -1) {
        val treeItem = new TreeItem[String]("Item "+i)

        root.getChildren().add(treeItem)

        for (j ← 2 + rand.nextInt(4) to 0 by -1) {
          val childItem = new TreeItem[String]("Child "+j)

          treeItem.getChildren().add(childItem)
        }
      }

      treeView
    }
}

//****************************************************************************
