
BOTTLING AND STORAGE PLANT
======================================
Problem description
This project considers the simulation of an energy drink bottling and packing plant. The plant has the following elements:

 A bottling and packing mechanism

 Three conveyor belts for delivering empty boxes, empty bottles and bottle caps to the bottling and packing mechanism

 A conveyor belt for the produced boxes

 Three platforms for stacking the boxes

 Three box transport drones

 Four storage units

Initially, the conveyor belts are loaded with 144 bottles, 144 caps and 12 empty boxes. The caps and bottles are numbered from T1 to T144 and from B1 to B144, respectively. The boxes have a label stating the box number (C1 to C12) and another label, which will be printed later, specifying the target storage unit (A to D). The storage units are in the same facility but separated from the bottling and packing section. The latter processes the elements in the conveyor belts in increasing order (for instance, T1, B1, and C1 in first place).

The operation of the plant is as follows:
 The bottling mechanism picks, from the conveyor belts, an empty box, 12 bottles, and the corresponding 12 caps. Then, the system fills and caps the bottles and places them in the box. The appropriate label with the storage unit tag is printed on the box, which is placed in the produced boxes’ conveyor belt. This whole operation takes three time units.

 Several workers organize the produced boxes in three stacks as they leave the system.Thus, each box is placed on top of one of the stacks on a rotating (round‐robin) basis.Assume no time is used in this task (it is included in the three time units of the previous point).

 There are three drones, each one associated to one of the box stacks and able to carry up to three boxes at once, transporting them to the storage units. The drones pick the boxes from its associated stack (starting from the top of the stack), placing them in the cargo hold, within the drone, for transportation. The boxes enter through one door and exit through another. For the sake of efficiency, the drones always load three boxes unless the boxes’ production is over. A drone uses seven time units since the moment it loads the boxes until the moment it is back to load a new batch.

 The first four produced boxes must be stored in the first storage unit (A), the next four in the second unit (B), the next two in the third unit (C), and the remaining boxes in the fourth unit (D).

 The boxes are stacked in each storage unit.

    Goal and requirements
    
The goal of the project is to build a Java program that, using the appropriate data structures (Stack and Queue), simulates the behavior of the previously described system. The program must show:

 The total time to store all the boxes

 The final content of each storage unit

Use generic classes when necessary. Casting is not allowed
