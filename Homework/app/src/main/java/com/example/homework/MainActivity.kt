
import android.app.Activity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.text.isDigitsOnly
import androidx.core.widget.doOnTextChanged
import com.example.homework.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private var viewBinding : ActivityMainBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)

        viewBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(viewBinding?.root)
        //val participants = GetParticipants()
        initViews()

    }

    private fun initViews() {
        viewBinding?.apply {
            raceBtn?.isEnabled = false
            amountOfParticipants.doOnTextChanged { text, start, before, count ->
                raceBtn?.isEnabled = text.isNullOrBlank().not() && text.toString().isDigitsOnly()
            }
        }

        viewBinding?.raceBtn?.setOnClickListener{
            val participants = viewBinding?.amountOfParticipants?.text.toString().toInt()
            val winner = startRaces(GetParticipants(participants))
            println("race winner - ${winner.brand + " " + winner.model}")
        }
    }

    private fun GetParticipants(n: Int): MutableList<Car> {
        val brands = listOf("BMW", "Lada", "Mercedes", "Mazda", "Chevrolet")
        val raceParticipants = mutableListOf<Car>()
        for (i in 0 until n) {
            val car = RandomCarsBuilder(brands.random())
            val methodArray = arrayOf(
                { car.BuildRandomCrossover() },
                { car.BuildRandomHatchback() },
                { car.BuildRandomSupercar() },
                { car.BuildRandomRoadster() }
            )

            raceParticipants.add(methodArray.random().invoke())
        }
//        raceParticipants.forEach { car ->
//            car.ShowInfo()
//        }
        return raceParticipants
    }

    private fun Race(p1 : Car, p2 : Car) : Car{
        return if(p1.to100 > p2.to100) p1 else p2
    }


    private fun startRaces(participants: MutableList<Car>): Car {
        var currentParticipants = participants
        while (currentParticipants.size > 1) {
            val nextRoundParticipants = mutableListOf<Car>()
            currentParticipants.shuffle()
            val numPairs = currentParticipants.size / 2

            for (i in 0 until numPairs) {
                val p1 = currentParticipants[i * 2]
                val p2 = currentParticipants[i * 2 + 1]
                val winner = Race(p1, p2)
                println("Гонка между ${p1.brand + " " + p1.model} и ${p2.brand + " " + p2.model}," +
                        " Победитель: ${winner.brand + " " + winner.model}")
                nextRoundParticipants.add(winner)
            }

            if (currentParticipants.size % 2 != 0) {
                val alone = currentParticipants.last()
                println("${alone.brand + alone.model} - Нет пары, следующий круг")
                nextRoundParticipants.add(alone)
            }

            currentParticipants = nextRoundParticipants
        }
        return currentParticipants[0]
    }

}

    open class Car(
        val brand: String,
        val model: String,
        val color: String,
        val year: Int,
        val to100: Double
    ) {
        open fun ShowInfo() {
            print("Basic CarInfo $brand $model $color $year 0-100km/h - $to100 ")
        }
    }

    class Crossover(
        brand: String,
        model: String,
        color: String,
        year: Int,
        to100: Double,
        val transmission: String
    ) : Car(brand, model, color, year, to100) {
        override fun ShowInfo() {
            super.ShowInfo()
            println("Additional Info: $transmission")
        }
    }

    class Supercar(
        brand: String,
        model: String,
        color: String,
        year: Int,
        to100: Double,
        val maxSpeed: Int,
        val doors: Int
    ) : Car(brand, model, color, year, to100) {
        override fun ShowInfo() {
            super.ShowInfo()
            println("Additional Info:  max Speed - $maxSpeed, doors amount - $doors")
        }
    }

    class Hatchback(
        brand: String,
        model: String,
        color: String,
        year: Int,
        to100: Double,
        val trunkVolume: Int,
        val cabinCapacity: Int
    ) : Car(brand, model, color, year, to100) {
        override fun ShowInfo() {
            super.ShowInfo()
            println("Additional Info: trunk V - $trunkVolume, Cabin Capacity - $cabinCapacity")
        }
    }

    class Roadster(
        brand: String,
        model: String,
        color: String,
        year: Int,
        to100: Double,
        val hp: Int,
        val maxSpeed: Int
    ) : Car(brand, model, color, year, to100) {
        override fun ShowInfo() {
            super.ShowInfo()
            println("Additional Info: hp - $hp, max speed - $maxSpeed")
        }
    }

    class RandomCarsBuilder(val brand: String) {
        val models = listOf("Model 1", "Model 2", "Model 3", "Model 4")
        val years = (2000..2024).toList()
        val colors = listOf("Red", "Blue", "White", "Black", "Silver")
        val to100s = listOf(3.0, 3.5, 4.0, 4.5, 5.0)
        val transmissions = listOf("FWD", "RWD", "AWD", "4WD")
        val maxSpeeds = (180..350 step 10).toList()
        val doorsAmounts = listOf(2, 3, 4, 5)
        val trunkVolumes = (300..700 step 50).toList()
        val cabinCapacities = listOf(2, 4, 5, 6, 7)
        val hPs = (100..500 step 50).toList()
        val methods: Array<() -> Unit> = arrayOf()
        fun BuildRandomCar(): Car {
            return Car(brand, models.random(), colors.random(), years.random(), to100s.random())
        }

        fun BuildRandomCrossover(): Crossover {
            val car = BuildRandomCar()
            return Crossover(
                car.brand, car.model, car.color, car.year,
                car.to100, transmissions.random()
            )
        }

        fun BuildRandomSupercar(): Supercar {
            val car = BuildRandomCar()
            return Supercar(
                car.brand, car.model, car.color, car.year,
                car.to100, maxSpeeds.random(), doorsAmounts.random()
            )
        }

        fun BuildRandomHatchback(): Hatchback {
            val car = BuildRandomCar()
            return Hatchback(
                car.brand, car.model, car.color, car.year,
                car.to100, trunkVolumes.random(), cabinCapacities.random()
            )
        }

        fun BuildRandomRoadster(): Roadster {
            val car = BuildRandomCar()
            return Roadster(
                car.brand, car.model, car.color, car.year,
                car.to100, hPs.random(), maxSpeeds.random()
            )
        }

    }


