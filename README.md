# FengLiuToyRobot
This is a toy robot Android app created by Feng Liu

# Description
A toy robot is placed on a 5*5 square table, there are no obstructions on the table surface. Enter the command to control the robot moving or turning.

# Command
Type command on the top of screen, all commands must be from the list below. Invalid command will stop robot movement. Each command must be separated by ONE white space
1. PLACE: place the robot on the table. Other commands cannot be excuted until the robot is on the table. Direction string must follow the place command and be separated by comma and no white space. The format is [position x, position y, face to]. There are only four directions: NORTH, EAST, SOUTH, WEST. Example of place command: PLACE 1,1,NORTH 
2. MOVE: move robot 1 step to the direction which robot is facing to. If robot moves out of the table's bound, the error is raised and the rest commands will be stopped
3. LEFT: rotate the robot 90 degrees on anti-clockwise
4. RIGHT: rotate the robot 90 degrees on clockwise
5. REPORT: print out the current position of the robot. Report command will stop any following command

# Constraints
1. robot must move only within the table range. No command is allowed to be excutable if robot is not on the table.
2. Each command is separated by ONE white space
3. Direction is separated by comma and no white space between them

# Notes
After command is excuted and, an output message of robot's current position will be displayed at the bottom of screen if command contains "Report". There is also an arrow will stay on the screen to present robot's position and direction. 

# Testing
1. PLACE 1,2,NORTH MOVE MOVE LEFT LEFT MOVE RIGHT REPORT Output: 1, 3, WEST
2. PLACE 4,4,WEST MOVE LEFT MOVE RIGHT REPORT Output: 3, 3, WEST
3. PLACE 3,2,SOUTH MOVE LEFT MOVE RIGHT MOVE MOVE REPORT Output: Robot is out of bound