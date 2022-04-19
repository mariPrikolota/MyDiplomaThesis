package com.example.myapplication


import android.os.Bundle
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.bd.RoomAppDB
import com.example.myapplication.drawers.ElementsDrawer
import com.example.myapplication.enums.Direction.*
import com.example.myapplication.drawers.GridDrawer
import com.example.myapplication.drawers.PandaDrawer
import com.example.myapplication.drawers.StepDrawer
import com.example.myapplication.enums.Material
import com.example.myapplication.level.LevelSave
import com.example.myapplication.models.Step
import kotlinx.android.synthetic.main.game_layout.*
import kotlinx.android.synthetic.main.game_over_layout.*

const val CELL_SIZE = 60
const val HORIZONTAL_CELL_AMOUNT = 24
const val VERTICAL_CELL_AMOUNT = 25
const val MAX_VERTICAL = VERTICAL_CELL_AMOUNT * CELL_SIZE
const val MAX_HORIZONTAL = HORIZONTAL_CELL_AMOUNT * CELL_SIZE

class GamePlayer: AppCompatActivity(), OnGameOverDialogButtonClickListener { //, View.OnClickListener
    private lateinit var myPanda: ImageView
    private var editMode = false
    var againMode = false


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


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.game_layout)
        onKeyButton()
        editMode = intent.getBooleanExtra("editMode", false)
//        againMode = intent.getBooleanExtra("againMode", false)
//        elementDrawer.drawElementsList(levelSave.loadLevel())
        switchEditMode()
        myPanda = findViewById(R.id.myPanda)
        container.layoutParams = FrameLayout.LayoutParams(MAX_VERTICAL, MAX_HORIZONTAL)
        container.setOnTouchListener { _, motionEvent ->
            elementDrawer.onTouchContainer(motionEvent.x, motionEvent.y)
            return@setOnTouchListener true
        }
    }

    private fun onKeyButton(){
        upView.setOnClickListener {
            stepDrawer.stepView(UP)
        }
        downView.setOnClickListener {
            stepDrawer.stepView(DOWN)
        }
        leftView.setOnClickListener {
            stepDrawer.stepView(LEFT)
        }
        rightView.setOnClickListener {
            stepDrawer.stepView(RIGHT)
        }
        eatView.setOnClickListener {
            stepDrawer.stepView(EAT)
        }

        deleteStep.setOnClickListener {
            while (stepDrawer.position  > 0){
                stepDrawer.eraseStep(stepDrawer.position - 1)
            }
          }
        startGame.setOnClickListener{
            goingOnList(stepDrawer.stepOnContainer)
            Thread.sleep(400)
            GameOver(this).show(supportFragmentManager, "GameOver")
        }

        functionMaterialView.setOnClickListener {  }
        functionView.setOnClickListener { }

        emptyView.setOnClickListener { elementDrawer.currentMaterial = Material.EMPTY }
        stoneView.setOnClickListener { elementDrawer.currentMaterial = Material.STONE }
        treeView.setOnClickListener { elementDrawer.currentMaterial = Material.TREE }
        bambooView.setOnClickListener { elementDrawer.currentMaterial = Material.BAMBOO }
//        pandaView.setOnClickListener { elementDrawer.currentMaterial = Material.PANDA }

        saveView.setOnClickListener {
//            levelSave.saveLevel(elementDrawer.elementsOnContainer)
            val listEventDao = RoomAppDB.getAppDB(application)?.levelDao()
        }


    }

    override fun onGameAgainClickListener(boolean: Boolean) {
        againMode = boolean
        if (againMode){
            finish()
            startActivity(intent)
        }
//        againMode = !boolean
    }

//    private fun startStepPanda(){
//        Thread(Runnable {
//            goingOnList(stepDrawer.stepOnContainer)
//            Thread.sleep(300)
//        }).start()
//    }

    private fun goingOnList(steps: List<Step>) {
 //       Thread(Runnable {
            for (stepOnList in steps) {
                when (stepOnList.step) {
                    UP -> pandaDrawer.move(myPanda, UP, elementDrawer.elementsOnContainer, elementDrawer.elementsOnContainer)
                    DOWN -> pandaDrawer.move(myPanda, DOWN, elementDrawer.elementsOnContainer, elementDrawer.elementsOnContainer)
                    LEFT -> pandaDrawer.move(myPanda, LEFT, elementDrawer.elementsOnContainer, elementDrawer.elementsOnContainer)
                    RIGHT -> pandaDrawer.move(myPanda, RIGHT, elementDrawer.elementsOnContainer, elementDrawer.elementsOnContainer)
                    EAT -> pandaDrawer.move(myPanda, EAT, elementDrawer.elementsOnContainer, elementDrawer.elementsOnContainer)
                }
//                Thread.sleep(300)
            }
//        }).start()
    }


    private fun switchEditMode(){
        if (editMode){
            gridDrawer.drawGrid()
            materialContainer.visibility = View.VISIBLE
            stepContainer.visibility = View.GONE
        }else{
            gridDrawer.removeGrid()
            elementDrawer.currentMaterial = Material.NULL
            materialContainer.visibility = View.GONE
            stepContainer.visibility = View.VISIBLE
        }
        editMode = !editMode
    }
}
