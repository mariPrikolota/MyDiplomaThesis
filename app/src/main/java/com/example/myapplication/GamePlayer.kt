package com.example.myapplication


import android.content.Intent
import android.os.Bundle
import android.os.SystemClock
import android.util.Log
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.bd.Level
import com.example.myapplication.bd.RoomAppDB
import com.example.myapplication.dialog.GameOver
import com.example.myapplication.dialog.OnGameOverDialogButtonClickListener
import com.example.myapplication.drawers.*
import com.example.myapplication.enums.Direction.*
import com.example.myapplication.enums.Material
import com.example.myapplication.level.LevelSave
import com.example.myapplication.models.Step
import kotlinx.android.synthetic.main.game_layout.*
import kotlinx.android.synthetic.main.level_shablon.*
import kotlin.math.log

const val CELL_SIZE = 60
const val HORIZONTAL_CELL_AMOUNT = 24
const val VERTICAL_CELL_AMOUNT = 25
const val MAX_VERTICAL = VERTICAL_CELL_AMOUNT * CELL_SIZE
const val MAX_HORIZONTAL = HORIZONTAL_CELL_AMOUNT * CELL_SIZE
var sizeElements = 2

//interface OnNewContainerClick{
//    fun newContainer(boolean: Boolean)
//}

class GamePlayer: AppCompatActivity(), OnGameOverDialogButtonClickListener, OnSizeElementsButton{ //, View.OnClickListener
    private lateinit var myPanda: ImageView
    private var editMode = false
    var againMode = false
    var stepsGame = true
//    lateinit var stepListTwo : ArrayList<Step>

    private val gridDrawer by lazy {
        GridDrawer(container)
    }

    private val elementDrawer by lazy {
        ElementsDrawer(container)
    }

    private  val pandaDrawer by lazy {
        PandaDrawer(container)
    }
    private val levelSave by lazy {
        LevelSave(this)
    }
    private val stepDrawer by lazy {
        StepDrawer(containerStep, this)
    }

    private val stepOneDrawer by lazy {
        StepDrawer(functionOneView, this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.game_layout)
        editMode = intent.getBooleanExtra("editMode", false)
        val textLevel = intent.getStringExtra("level")
        switchEditMode()
        elementDrawer.drawElementsList(textLevel?.let { levelSave.loadLevel(it) })
        textLevel?.let { openLevel() }
//        stepListTwo = stepOnContainer as ArrayList<Step>
        functionClick()
        onKeyButton()

        container.layoutParams = FrameLayout.LayoutParams(MAX_VERTICAL, MAX_HORIZONTAL)
        container.setOnTouchListener { _, motionEvent ->
            elementDrawer.onTouchContainer(motionEvent.x, motionEvent.y)
            return@setOnTouchListener true
        }
    }

    override fun onStop() {
        super.onStop()
        numberStepFunction = true
        deleteList()
    }

    private fun openLevel(){
        if (elementDrawer.myPanda != null) {
            myPanda = elementDrawer.myPanda!!
        }
    }

    private fun functionClick(){
        functionOne.setOnClickListener{
            stepsGame = false
            numberStepFunction = false
        }
        linearContainer.setOnClickListener {
            stepsGame = true
            numberStepFunction = true
        }
    }

    private fun deleteList(){
        stepOnContainer.clear()
        stepFunctionContainer.clear()
    }

    private fun onKeyButton(){
        upView.setOnClickListener {
            if (stepsGame){
                stepDrawer.stepView(UP)
            }else{
                stepOneDrawer.stepView(UP)
            }

        }
//        downView.setOnClickListener {
//                if (stepsGame){
//                    stepDrawer.stepView(DOWN)
//                }else{
//                    stepOneDrawer.stepView(DOWN)
//                }
//        }
        leftView.setOnClickListener {
                if (stepsGame){
                    stepDrawer.stepView(LEFT)
                }else{
                    stepOneDrawer.stepView(LEFT)
                }
        }
        rightView.setOnClickListener {
                if (stepsGame){
                    stepDrawer.stepView(RIGHT)
                }else{
                    stepOneDrawer.stepView(RIGHT)
                }
        }
        eatView.setOnClickListener {
                if (stepsGame){
                    stepDrawer.stepView(EAT)
                }else{
                    stepOneDrawer.stepView(EAT)
                }
        }
        functionMaterialView.setOnClickListener {
            if(stepsGame){
                stepDrawer.stepView(FUN)
//                stepListTwo = (stepOnContainer + stepFunctionContainer) as ArrayList<Step>
                stepOnContainer = (stepOnContainer + stepFunctionContainer).toMutableList()

                Log.d("steps",  stepOnContainer.toString())
            }
        }

        deleteStep.setOnClickListener {
                stepDrawer.eraseStep()
        }

        home.setOnClickListener {
            finish()
        }

        startGame.setOnClickListener{
            goingOnList(stepOnContainer)
 //           myPanda.rotation = 0f
            Thread {
                Thread.sleep(1000)
                GameOver(this).show(supportFragmentManager, "GameOver")
            }.start()
        }

        emptyView.setOnClickListener { elementDrawer.currentMaterial = Material.EMPTY }
        stoneView.setOnClickListener { elementDrawer.currentMaterial = Material.STONE }
        treeView.setOnClickListener { elementDrawer.currentMaterial = Material.TREE }
        bambooView.setOnClickListener { elementDrawer.currentMaterial = Material.BAMBOO }
        pandaView.setOnClickListener { elementDrawer.currentMaterial = Material.PANDA }
        setting.setOnClickListener { SizeMaterial(this).show(supportFragmentManager, "SizeMaterial") }

        saveView.setOnClickListener {
            val level = levelSave.saveLevel(elementDrawer.elementsOnContainer)
            val listEventDao = RoomAppDB.getAppDB(application)?.levelDao()
            listEventDao?.insertLevel(Level(id = 0, elementList = level))
            finish()
            val intent = Intent(this, LevelGame::class.java)
            startActivity(intent)
        }
    }

    override fun onGameAgainClickListener(boolean: Boolean) {
        againMode = boolean
        if (againMode){
            finish()
            startActivity(intent)
        }
        againMode = !boolean
    }

    override fun onFinishGame(boolean: Boolean) {
        againMode = boolean
        if (againMode){
            finish()
        }
        againMode = !boolean

    }

    private fun goingOnList(steps: List<Step>) {
        Thread(Runnable {
            for (stepOnList in steps) {
                Thread.sleep(500)
                runOnUiThread {
                    when (stepOnList.step) {
                        UP -> pandaDrawer.move(myPanda, UP, elementDrawer.elementsOnContainer, elementDrawer.elementsOnContainer)
//                        DOWN -> pandaDrawer.move(myPanda, DOWN, elementDrawer.elementsOnContainer, elementDrawer.elementsOnContainer)
                        LEFT -> pandaDrawer.move(myPanda, LEFT, elementDrawer.elementsOnContainer, elementDrawer.elementsOnContainer)
                        RIGHT -> pandaDrawer.move(myPanda, RIGHT, elementDrawer.elementsOnContainer, elementDrawer.elementsOnContainer)
                        EAT -> pandaDrawer.move(myPanda, EAT, elementDrawer.elementsOnContainer, elementDrawer.elementsOnContainer)
                    }
                }
            }
        }).start()
    }

    override fun elementsSize(size: Int) {
        sizeElements = size
    }

    private fun switchEditMode(){
        if (editMode){
            gridDrawer.drawGrid()
            elementDrawer.currentMaterial = Material.NULL
            materialContainer.visibility = View.VISIBLE
            stepContainer.visibility = View.GONE
            algorithmsContainer.visibility = View.GONE
        }else{
            gridDrawer.removeGrid()
            elementDrawer.currentMaterial = Material.NULL
            materialContainer.visibility = View.GONE
            stepContainer.visibility = View.VISIBLE
            algorithmsContainer.visibility = View.VISIBLE
            container
        }
        editMode = !editMode
    }


}
