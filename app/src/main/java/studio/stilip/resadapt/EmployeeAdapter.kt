package studio.stilip.resadapt

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class EmployeeAdapter(
    private val likeAction: (Int) -> Unit
) :
    RecyclerView.Adapter<EmployeeAdapter.EmployeeViewHolder>() {

    private var employees = mutableListOf<Employee>()

    inner class EmployeeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val card: ConstraintLayout = itemView.findViewById(R.id.card_employee)
        val photoImageView: ImageView = itemView.findViewById(R.id.photo)
        val fullnameTextView: TextView = itemView.findViewById(R.id.full_name)
        val departmentTextView: TextView = itemView.findViewById(R.id.department)
        val like: ImageView = itemView.findViewById(R.id.like)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmployeeViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.employees_list_item, parent, false)
        return EmployeeViewHolder(view)
    }

    override fun onBindViewHolder(holder: EmployeeViewHolder, position: Int) {
        val employee = employees[position]
        with(holder) {
            fullnameTextView.text = employee.fullName
            departmentTextView.text = employee.departament

            like.visibility = if (employee.isLiked) View.VISIBLE else View.INVISIBLE

            Glide.with(photoImageView.context)
                .load(employee.photoUrl)
                .centerCrop()
                .into(photoImageView)

            card.setOnClickListener {
                likeAction(position)
                like.visibility = if (employee.isLiked) View.VISIBLE else View.INVISIBLE
            }
        }

    }

    override fun getItemCount(): Int {
        return employees.size
    }

    fun reload(data: List<Employee>) {
        val diffUtil = EmployeesDiffUtilCallback(employees, data)
        val result = DiffUtil.calculateDiff(diffUtil)
        employees.clear()
        employees.addAll(data)
        result.dispatchUpdatesTo(this)
    }
}