package oxxy.kero.roiaculte.team7.processor.generators

interface Generator<T:Model> {
      fun generate(model: T)

}
interface Model