# MVVMTester

android architecture component에서 테스트 및 유지관리가 쉬운 앱을 설계하기 위한 여러 라이브러리를 소개 한다.

* kotlin code link : https://github.com/CharkoT/MVVMTester/tree/first_/app/src/main/java/com/charko/tester/mvvmtester/kotlin

이번에는 그중 mvvm, livedata를 파해쳐 보려 한다.

mvvm을 알기 전에 mvc, mvp구조를 한번 알아보자.

mvc 구조는 Android가장 기초적으로 제공되어진다. 

Model - View(layout xml) - controller(activity)

이 뿐만 아니라, MFC, ios개발에도 동일한 구조로 설계되어 있으며 동작한다.
그럼 mvc구조를 사용하지 왜 mvp, mvvm을 만드는것일까? 

mvc구조는 유닛테스트나 유지관리에 어려움이 있다.
activity를 만들때 하나의 mvc 구조가 생성 되며 여러 activity를 만들경우 각각의 vc가 만들어 진다.
모든 activity에서 c가 만들어 지는데 여기서 코드 재활용하기에 어려움이 많아 객체지향적이지 않고. 그래서 개발자들은 그들만의 능력을 통해 재활용할 코드를 사용할수 있는 가진 방법들을 사용했을것이다.
그래서 만들어진게 mvp구조이며 p에서 일정부분 해소를 할 수 있다. 하지만 v와 p의 1:1구조를 해결하지 못했고, 그래서 android에서는 mvvm구조를 추천하고 있는 상황이다.
물론 간단한 페이지 구현일 경우는 굳이 mvp, mvvm구조를 사용하지 않아도 된다.

mvvm구조의 장점을 알아보면 유지관리에 뛰어남을 보인다.
vm에서 각각의 기능을 수행하며, View관리에도 쉽다.(model의 변되는 값을 로드하며 관리 할 수있다), 메모리 누수 위험이 적다. nullpoint exception 발생이 없다.
view 와 viewmodel 관계는 1대 다 구조를 형성 할 수 있다.


Android MVVM + LiveData
<div>
<img width=800 src="https://user-images.githubusercontent.com/8044971/48749947-5e9ca400-ecc0-11e8-96a7-6efa4917ddbd.png">
</div>

Get in images from device
and print EXIF info

단말기 사진을 불러오고, 그 사진의 description을 작성하는 간단안 어플리케이션

activity 설명, MainActivity - 10개의 사진을 불러오고 recyclerView로 exif정보를 뿌려준다.

선택하면 ImageViewActivity로 들어가 description을 수정하고 뒤로 가면 저장이 된다.
