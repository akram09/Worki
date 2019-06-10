package oxxy.kero.roiaculte.team7.processor.generators


//
//class UseCaseGenerator  @Inject  constructor():Generator<UsecaseModel>{
//
//    override suspend fun  generate(model: UsecaseModel) =coroutineScope {
//        // create the file
//            val usecaseGeneratedFile = File(model.packageInfo.root, "")
//          // generate propertyImplementation for ResultDispatcher
//            val generatedMainDispatcherProperty = generateMainPropertyOverriding()
//        //generate propertyImplementation fro dispatcher
//            val generatedIoDispatcherProperty  = generateIoPropertyOverriding()
//        // generate property of the RepositoryClass  repo
//            val generatedRepositoryProperty =  generateRepoProperty(model.workiannotation.repositoryClass)
//          // generate invoke suspending function
//            val generatedInvokeFunction = generateInvokeFunction(model.input.className , model.failureClass , model.workiannotation.repositoryFunctionName , model.output.className)
//           // generate the class
//            val generatedUsecaseClass =   generateClass(model.packageInfo.name ,
//                model.superInterface.interfaceClass , model.workiannotation.repositoryClass , generatedIoDispatcherProperty.await() ,
//                generatedMainDispatcherProperty.await() , generatedRepositoryProperty.await() , generatedInvokeFunction.await())
//        //   generate the file
//            val spec = FileSpec.builder(model.packageInfo.packageName, "Generated_${model.packageInfo.name}")
//                .addType(generatedUsecaseClass.await()).build()
//        // write to the file and join
//            launch { spec.writeTo(usecaseGeneratedFile)}.join()
//        }
//
//
//    private fun CoroutineScope.generateClass(generatedUsecaseName:String ,useCaseSuperInterface:ClassName , usecaseRepositoryClass:ClassName,
//                              dispatcherIoPropertySpec: PropertySpec , dispatcherMainPropertySpec: PropertySpec,
//                              repoPropertySpec: PropertySpec , invokeFunctionFunSpec: FunSpec
//                              )
//            : Deferred<TypeSpec> = async {
//        TypeSpec.classBuilder("Generated_$generatedUsecaseName")
//            .addSuperinterface(useCaseSuperInterface)
//            .primaryConstructor(
//                FunSpec.constructorBuilder().addParameter("couroutineDispatchers"
//            , CouroutineDispatchers::class  ).addParameter("repo", usecaseRepositoryClass)
//                    .addAnnotation(Inject::class)
//                    .build()
//            )
//            .addProperty(dispatcherIoPropertySpec)
//            .addProperty(dispatcherMainPropertySpec)
//            .addProperty(repoPropertySpec)
//            .addFunction(invokeFunctionFunSpec)
//            .build()
//    }
//
//    private fun CoroutineScope.generateInvokeFunction(usecaseInputClass:ClassName, useCaseFailureClass:ClassName
//                                     ,
//                                       functionName:String,
//                                        usecaseOutputClass:ClassName):Deferred<FunSpec> = async{
//        FunSpec.builder("invoke")
//            .addParameter("executeParams", usecaseInputClass)
//           .returns(Either::class.asClassName().parameterizedBy(useCaseFailureClass , usecaseOutputClass))
//           .addCode("return repo.$functionName(executeParams)")
//            .addModifiers(KModifier.OVERRIDE).addModifiers(KModifier.SUSPEND).build()
//    }
//    private  fun CoroutineScope.generateIoPropertyOverriding():Deferred<PropertySpec> = async{
//        PropertySpec.builder("dispatcher", CoroutineDispatcher::class
//            , KModifier.OVERRIDE  ).initializer("couroutineDispatchers.computaion")
//            .build()
//    }
//
//    private fun CoroutineScope.generateMainPropertyOverriding():Deferred<PropertySpec> = async{
//         PropertySpec.builder("ResultDispatcher", CoroutineDispatcher::class , KModifier.OVERRIDE)
//            .initializer("couroutineDispatchers.main")
//            .build()
//    }
//    private  fun CoroutineScope.generateRepoProperty(repositoryClass:ClassName):Deferred<PropertySpec>  = async{
//        PropertySpec.builder("repo", repositoryClass)
//            .initializer("repo").build()
//    }
//
//}
//
