#!/bin/bash
#***************************** Copyright © Jonathon Bell. All rights reserved.
#**
#**
#**  Version : $Header:$
#**
#**
#**  Purpose : Packages the executable JAR as a self contained application. 
#**
#**
#**  See Also: https://docs.oracle.com/javase/9/tools/javapackager.htm#JSWOR719
#**
#**
#**  Comments: This file uses a tab size of 2 spaces.
#**                                                                     0-0
#**                                                                   (| v |)
#***********************************************************************w*w***

jar=$(basename $(ls -1 target/why*.jar))                 # The executable jar

if [[ -d target/Why.app ]]                               # Does target exist?
then
  rm -rf target/Why.app                                  # ...then remove it
fi

javapackager                                             \
  -deploy                                                \
  -srcdir       target                                   \
  -outdir       target                                   \
  -native       image                                    \
  -srcfiles     $jar                                     \
  -outfile      Why                                      \
  -name         Why                                      \
  -title        Why                                      \
  -appclass     com.wolery.why.why                       \
  -Bicon=src/main/resources/why.icns

#*****************************************************************************
