package oxxy.kero.roiaculte.team7.processor.generators

interface Generator<T:Model> {
     suspend fun generate(model: T)

}
interface Model