import com.lgaieta.classmanager.subjects.models.Subject
import kotlinx.coroutines.flow.Flow
import com.lgaieta.classmanager.subjects.services.SubjectRoomDao
import com.lgaieta.classmanager.subjects.models.SubjectRepository
import com.lgaieta.classmanager.subjects.services.SubjectRoomEntity
import com.lgaieta.classmanager.tasks.models.Task
import com.lgaieta.classmanager.students.models.Student
import kotlinx.coroutines.flow.map

class OfflineRoomSubjectRepository(private val subjectDao: SubjectRoomDao) : SubjectRepository {
    override fun getAllSubjectsStream(): Flow<List<Subject>> =
        subjectDao.getAllSubjects().map { list ->
            list.map {
                it.toSubject()
            }
        }

    override fun getSubjectStream(id: Int): Flow<Subject?> =
        subjectDao.getSubject(id).map { it?.toSubject() }

    override fun getTasksStream(subjectId: Int): Flow<List<Task>> =
        subjectDao.getTasks(subjectId).map { list ->
            list.map {
                it.toTask()
            }
        }

    override suspend fun insert(subject: Subject) =
        subjectDao.insert(SubjectRoomEntity.fromSubject(subject))

    override suspend fun delete(subject: Subject) =
        subjectDao.delete(SubjectRoomEntity.fromSubject(subject))

    override suspend fun update(subject: Subject) =
        subjectDao.update(SubjectRoomEntity.fromSubject(subject))
}