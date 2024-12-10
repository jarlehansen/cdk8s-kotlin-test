package org.example

import org.cdk8s.App
import org.cdk8s.Chart
import org.cdk8s.plus28.k8s.*
import software.amazon.jsii.Builder
import software.constructs.Construct

operator fun <T> Builder<T>.invoke(): T = this.build() as T

inline fun <reified T : Builder<*>> add(builder: T.() -> Unit): T {
    val instance = T::class.java.getDeclaredConstructor().newInstance()
    return instance.apply(builder)
}

class Main(scope: Construct, id: String) : Chart(scope, id) {
    init {
        KubeDeployment(
            this, "test",
            add<KubeDeploymentProps.Builder> {
                spec(add<DeploymentSpec.Builder> {
                    selector(add<LabelSelector.Builder> {}())
                    replicas(1)
                    template(add<PodTemplateSpec.Builder> {
                        paused(true)
                    }())
                }())
            }()
        )
    }
}

fun main() {
    val app = App()
    Main(app, "kotlin")
    app.synth()
}