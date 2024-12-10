package org.example

import org.cdk8s.App
import org.cdk8s.Chart
import org.cdk8s.plus28.k8s.DeploymentSpec
import org.cdk8s.plus28.k8s.KubeDeployment
import org.cdk8s.plus28.k8s.KubeDeploymentProps
import org.cdk8s.plus28.k8s.LabelSelector
import org.cdk8s.plus28.k8s.ObjectMeta
import org.cdk8s.plus28.k8s.PodTemplateSpec
import software.amazon.jsii.Builder
import software.constructs.Construct
import kotlin.jvm.java

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
                    template(add<PodTemplateSpec.Builder> {}())
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