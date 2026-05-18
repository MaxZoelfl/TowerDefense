# THURSDAY, 07 MAY

## Leon
Button() ; GameWorld()
A provisional 'tower creation button' has been implemented. Pressing it changes the selectedButton attribute in the GameWorld() class.

## Max

## Leopold  


# FRIDAY, 08 MAY
## Max
Move() implemetation in Enemy class. Is following the path but nor perfect yet. Has to be improved.

## Leon
Tile() ; Grass() ; Path() ; PathCorner() ; GameWorld()  
Leopold's pixel grass tiles and path tiles have been integrated into the map. The straight tiles rotate according to the path, but there is a problem with their positioning after rotating (resulting in small gaps between individual tiles). The corner path tiles have not yet been integrated.  


# TUESDAY, 12 MAY

## Leon
GameWorld() ; Enemy()  
Corner path tiles have been integrated into the map.

## Max
Gaps between tiles (resulting after rotating) have been fixed with Mr Rau's tip.  


# MONDAY, 18 MAY

## Leon
Push 1:  
[main 5b90b9e] Gaps between tiles have been fixed w/o scaling up the tiles before rotating  
 14 files changed, 18 insertions(+), 22 deletions(-)  
 rename PROJEKT/images/{PathCorner.png => PathCorner_0.png} (100%)  
 create mode 100644 PROJEKT/images/PathCorner_180.png  
 create mode 100644 PROJEKT/images/PathCorner_270.png  
 create mode 100644 PROJEKT/images/PathCorner_90.png  
 rename PROJEKT/images/{Path.png => Path_0.png} (100%)  
 create mode 100644 PROJEKT/images/Path_90.png  

Push 2:  
[main 3f43951] Many many changes  
 34 files changed, 165 insertions(+), 153 deletions(-)  
 delete mode 100644 PROJEKT/Arrow.class  
 create mode 100644 PROJEKT/Bow.class  
 rename PROJEKT/{Arrow.ctxt => Bow.ctxt} (89%)  
 rename PROJEKT/{Arrow.java => Bow.java} (80%)  
 delete mode 100644 PROJEKT/Clock.class  
 delete mode 100644 PROJEKT/Clock.ctxt  
 delete mode 100644 PROJEKT/Clock.java  
 create mode 100644 PROJEKT/GameConstants.class  
 create mode 100644 PROJEKT/GameConstants.ctxt  
 create mode 100644 PROJEKT/GameConstants.java  
 delete mode 100644 PROJEKT/PlayButton.class  
 delete mode 100644 PROJEKT/PlayButton.ctxt  
 delete mode 100644 PROJEKT/PlayButton.java  
 rename PROJEKT/{Bullet.class => Projectile.class} (82%)  
 rename PROJEKT/{Bullet.ctxt => Projectile.ctxt} (86%)  
 rename PROJEKT/{Bullet.java => Projectile.java} (91%)  