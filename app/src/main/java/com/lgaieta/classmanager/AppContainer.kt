package com.lgaieta.classmanager

import com.lgaieta.classmanager.subjects.models.SubjectRepository

interface AppContainer {
    val offlineSubjectRepository: SubjectRepository
}

