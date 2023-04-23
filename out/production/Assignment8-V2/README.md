model.IShape: interface of shapes

model.AbstractShape: abstract class that extends model.IShape interface

model.Rectangle / model.Oval : concrete class that implements model.IShape interface

model.ShapePosition is a wrapper class that composed of shape and its position on photo album

model.SnapShots class is responsible for taking snapshots.

model.Canvas is the implementation of photo album. It has the following major functionality: create shape, 
move shape, change shape width/height, place shape on photo album, take snapshots, show a list of all snapshots taken,
show history of all commands used, and reset the photo album. CreateShape create new model.Rectangle/model.Oval class depending on the input.
MoveShape links shapePosition objects and change shape's position through shapePosition class. 
changeShapeColor/ changeShapeX/ changeShapeY method is performed through linking shapePosition 
and model.IShape class. PlaceShape methods creates new shapePosition object and store it in a list. 
TakeSnapshot methods create new model.SnapShots objects and store it in a list;



//New content

Added remove function to canvas. 


command to run:
java -jar Assignment8-V2.jar -in /Users/zhangkaiqi/Documents/NEU-MSCS-Align/CS5004/HWs/Assignment9-Final/src/controller/buildings.txt -view graphical -out /Users/zhangkaiqi/Documents/NEU-MSCS-Align/CS5004/HWs/Assignment9-Final/src/view/out.html 900 800
