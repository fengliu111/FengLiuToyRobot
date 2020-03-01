class Robot {
    private var posX: Int = -1
    private var posY: Int = -1
    private var faceTo: String = ""
    private var table = Table()
    enum class Direction{
        NORTH, EAST, SOUTH, WEST
    }

    fun place(x:Int, y:Int, f:String){
        if(x < 0 || x > table.getWidth() || y < 0 || y > table.getHeight()) {
            throw Exception("Please place robot within the range")
        }
        else if(!Direction.values().any { it.name == f.toUpperCase() }){
            throw Exception("Direct must be \"NORTH\", \"EAST\", \"SOUTH\" or \"WEST\"")
        }
        else{
            this.posX = x
            this.posY = y
            this.faceTo = f.toUpperCase()
        }
    }

    fun move(){
        when(this.faceTo){
            "NORTH" -> posY++
            "EAST" -> posX++
            "SOUTH" -> posY--
            "WEST" -> posX--
        }

        if(checkOutOfBound())
            throw Exception("Robot is out of bound")
    }

    fun left(){
        var i = Direction.valueOf(faceTo).ordinal - 1
        if(i < 0)
            i = 3

        faceTo = Direction.values()[i].name
    }

    fun right(){
        var i = Direction.valueOf(faceTo).ordinal + 1
        if(i > 3)
            i = 0

        faceTo = Direction.values()[i].name
    }

    fun report(): String{
        return "Output: ${posX}, ${posY}, ${faceTo}"
    }

    fun getTable(): Table{
        return this.table
    }

    fun getPosX(): Int{
        return this.posX
    }

    fun getPosY(): Int{
        return this.posY
    }

    fun getFaceTo(): String{
        return this.faceTo
    }

    private fun checkOutOfBound(): Boolean{
        if(posX < 0 || posX > table.getWidth() || posY < 0 || posY > table.getHeight())
            return true

        return false
    }
}