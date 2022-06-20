package com.example.myapplication


import android.annotation.SuppressLint
import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.bd.Level
import com.example.myapplication.bd.RoomAppDB
import com.example.myapplication.dialog.*
import com.example.myapplication.drawers.*
import com.example.myapplication.enums.Direction
import com.example.myapplication.enums.Direction.*
import com.example.myapplication.enums.Material
import com.example.myapplication.level.LevelSave
import com.example.myapplication.models.Step
import kotlinx.android.synthetic.main.game_layout.*

const val CELL_SIZE = 60
const val HORIZONTAL_CELL_AMOUNT = 24
const val VERTICAL_CELL_AMOUNT = 25
const val MAX_VERTICAL = VERTICAL_CELL_AMOUNT * CELL_SIZE
const val MAX_HORIZONTAL = HORIZONTAL_CELL_AMOUNT * CELL_SIZE
var sizeElements = 2

class GamePlayer: AppCompatActivity(), OnGameOverDialogButtonClickListener, OnStopGameClickListener{ //, View.OnClickListener , OnStopGameClickListener
    private lateinit var myPanda: ImageView
    private var editMode = false
    var againMode = false
    var stepsGame = true
    var stopGame = false

    private val elementDrawer by lazy {
        ElementsDrawer(container)
    }

    private  val pandaDrawer by lazy {
        PandaDrawer(container, this)
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

    private val levelDrawer by lazy {
        LevelDrawer(flameLayout)
    }

    private val soundManager by lazy {
        SoundManager(this)
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.game_layout)
        editMode = intent.getBooleanExtra("editMode", false)
        soundManager.soundStart()
        switchEditMode()
        studyLevelGame()
        levelDrawer.stepStudyLevel(intent.getStringExtra("idStudyLevel"))
        functionClick()
        onKeyButton()
        Log.d("tag", numberBamboo.toString())
        container.layoutParams = FrameLayout.LayoutParams(MAX_VERTICAL, MAX_HORIZONTAL)
        container.setOnTouchListener { _, motionEvent ->
            elementDrawer.onTouchContainer(motionEvent.x, motionEvent.y)
            return@setOnTouchListener true
        }
    }

    override fun onPause() {
        super.onPause()
        numberStepFunction = true
        deleteList()
        numberBamboo = 0
        soundManager.soundStop()
    }

    private fun studyLevelGame(){
        val textLevel = intent.getStringExtra("level")
        elementDrawer.drawElementsList(textLevel?.let { levelSave.loadLevel(it) })
        textLevel?.let { openLevel() }

        val textStudyLevel = intent.getStringExtra("levelStudy")
        elementDrawer.drawElementsList(textStudyLevel?.let { levelSave.loadLevel(it) })
        textStudyLevel?.let { openLevel() }
    }

    @SuppressLint("ResourceAsColor")
    private fun functionClick() {
        functionOne.setOnClickListener{
            stepsGame = false
            numberStepFunction = false
            this.resources?.getColor(R.color.white, null)
                ?.let { it1 -> functionOne.background.setTint(it1) }
            this.resources?.getColor(R.color.gray, null)
                ?.let { it1 -> linearContainer.background.setTint(it1) }
        }
        linearContainer.setOnClickListener {
            stepsGame = true
            numberStepFunction = true
            this.resources?.getColor(R.color.white, null)
                ?.let { it1 -> linearContainer.background.setTint(it1) }
            this.resources?.getColor(R.color.gray, null)
                ?.let { it1 -> functionOne.background.setTint(it1) }
        }
    }

    private fun deleteList() {
        stepOnContainer.clear()
        stepFunctionContainer.clear()

    }

    private fun onKeyButton() {
        upView.setOnClickListener {
            if (stepsGame){
                stepDrawer.stepView(UP)
            }else{
                stepOneDrawer.stepView(UP)
            }
        }
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
        cornerRightView.setOnClickListener {
            if (stepsGame){
                stepDrawer.stepView(CORNER_RIGHT)
            }else{
                stepOneDrawer.stepView(CORNER_RIGHT)
            }
        }
        cornerLeftView.setOnClickListener {
            if (stepsGame){
                stepDrawer.stepView(CORNER_LEFT)
            }else{
                stepOneDrawer.stepView(CORNER_LEFT)
            }
        }
        jumpView.setOnClickListener {
            if (stepsGame){
                stepDrawer.stepView(JUMP)
            }else{
                stepOneDrawer.stepView(JUMP)
            }
        }
        functionMaterialView.setOnClickListener {
            if(stepsGame){
                stepDrawer.stepView(FUN)
                stepOnContainer = (stepOnContainer + stepFunctionContainer).toMutableList()
            }
        }

        deleteStep.setOnClickListener { stepDrawer.eraseStep() }
        deleteFunStep.setOnClickListener {
            stepDrawer.eraseStep()
            stepOneDrawer.eraseStep() }

        home.setOnClickListener { finish() }

        finishMaterial.setOnClickListener { openLevelActivity() }

        startGame.setOnClickListener {
            goingOnList(stepOnContainer)
            stepContainer.visibility = View.GONE
        }

        emptyView.setOnClickListener { elementDrawer.currentMaterial = Material.EMPTY }
        stoneView.setOnClickListener { elementDrawer.currentMaterial = Material.STONE }
        treeView.setOnClickListener { elementDrawer.currentMaterial = Material.TREE }
        bambooView.setOnClickListener { elementDrawer.currentMaterial = Material.BAMBOO }
        pandaView.setOnClickListener { elementDrawer.currentMaterial = Material.PANDA }
        setting.setOnClickListener { SizeMaterial().show(supportFragmentManager, "SizeMaterial") }

        saveView.setOnClickListener {
            if (checkPandaOnContainer()){
                val level = levelSave.saveLevel(elementDrawer.elementsOnContainer)
                val listEventDao = RoomAppDB.getAppDB(application)?.levelDao()
                listEventDao?.insertLevel(Level(id = 0, elementList = level))
                Log.d("level", level)
                openLevelActivity()
                }else{
                    ForgotPanda().show(supportFragmentManager, "ForgotPanda")
                }
        }
    }

    private fun checkPandaOnContainer(): Boolean{
        var panda = 0
        for (elem in elementDrawer.elementsOnContainer) {
            if (elem.material == Material.PANDA) panda += 1
            if (elem.material == Material.BAMBOO){
                if (panda == 1) return true
            }
        }
        return false
    }

    private fun openLevel() {
        if (elementDrawer.myPanda != null) {
            myPanda = elementDrawer.myPanda!!
        }
    }

    private fun openLevelActivity() {
        finish()
        val intent = Intent(this, LevelGame::class.java)
        startActivity(intent)
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
                Thread.sleep(300)
                runOnUiThread {
                    when (stepOnList.step) {
                        UP -> {
                            onPandaStep(UP)
                        }
                        LEFT -> onPandaStep(LEFT)
                        RIGHT -> onPandaStep(RIGHT)
                        JUMP -> onPandaStep(JUMP)
                        CORNER_LEFT -> onPandaStep(CORNER_LEFT)
                        CORNER_RIGHT ->  onPandaStep(CORNER_RIGHT)
                        EAT -> onPandaStep(EAT)
                    }
                }
                if (stopGame) {
                    break
                }
            }
            Thread.sleep(300)
            Log.d("tag", numberBamboo.toString())
            if (!stopGame) {
                GameOver(this).show(supportFragmentManager, "GameOver")
            }
        }).start()
    }

    private fun onPandaStep(direction: Direction) {
        pandaDrawer.move(myPanda, direction, elementDrawer.elementsOnContainer, elementDrawer.elementsOnContainer)
    }

    @SuppressLint("ResourceAsColor")
    private fun switchEditMode() {
        if (editMode){
            elementDrawer.currentMaterial = Material.NULL
            materialContainer.visibility = View.VISIBLE
            stepContainer.visibility = View.GONE
            algorithmsContainer.visibility = View.GONE
        }else{
            elementDrawer.currentMaterial = Material.TREE
            materialContainer.visibility = View.GONE
            stepContainer.visibility = View.VISIBLE
            algorithmsContainer.visibility = View.VISIBLE
            container
        }
        editMode = !editMode
    }

    override fun stopGame(boolean: Boolean) {
        stopGame = boolean
        Log.d("boo", stopGame.toString())
        if (stopGame) {
            GameOver(this).show(supportFragmentManager, "GameOver")
        }
    }
}
