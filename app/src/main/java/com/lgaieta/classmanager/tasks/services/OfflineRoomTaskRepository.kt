import com.lgaieta.classmanager.tasks.models.Task

import kotlinx.coroutines.flow.Flow
import com.lgaieta.classmanager.tasks.models.TaskRepository
import com.lgaieta.classmanager.tasks.services.TaskRoomDao
import com.lgaieta.classmanager.tasks.services.TaskRoomEntity
import kotlinx.coroutines.flow.map

class OfflineRoomTaskRepository(private val taskDao: TaskRoomDao) : TaskRepository {
    override fun getAllTasksStream(): Flow<List<Task>> =
        taskDao.getAllTasks().map { list -> list.map { it.toTask() } }

    override fun getTaskStream(id: Int): Flow<Task?> =
        taskDao.getTask(id).map { it.toTask() }

    override suspend fun insert(task: Task) =
        taskDao.insert(TaskRoomEntity.fromTask(task))

    override suspend fun delete(task: Task) =
        taskDao.delete(TaskRoomEntity.fromTask(task))

    override suspend fun update(task: Task) =
        taskDao.update(TaskRoomEntity.fromTask(task))
}