package studio.stilip.resadapt

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.DiffUtil
import java.text.FieldPosition

class MainActivityViewModel : ViewModel() {

    var employees = MutableLiveData(Employee.getMockEmployees())

    fun likeEmployee(position: Int) {
            employees.value?.get(position)?.isLiked = !(employees.value?.get(position)?.isLiked)!!
    }
}
