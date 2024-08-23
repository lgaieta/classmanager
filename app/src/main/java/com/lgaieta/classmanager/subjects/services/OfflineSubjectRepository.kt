import com.lgaieta.classmanager.subjects.models.Subject
import kotlinx.coroutines.flow.Flow
import com.lgaieta.classmanager.subjects.services.SubjectRoomDao
import com.lgaieta.classmanager.subjects.models.SubjectRepository

class OfflineSubjectRepository(private val subjectDao: SubjectRoomDao) : SubjectRepository {
    override fun getAllSubjectsStream(): Flow<List<Subject>> = subjectDao.getAllSubjects()

    override fun getSubjectStream(id :Int):Flow<Subject?> = subjectDao.getSubject(id)

    override suspend fun insert(subject: Subject) = subjectDao.insert(subject)

    override suspend fun delete(subject: Subject) = subjectDao.delete(subject)

    override suspend fun update(subject: Subject) = subjectDao.update(subject)
}